package com.tencoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencoding.blog.dto.KakaoProfile;
import com.tencoding.blog.dto.KakaoProfile.KakaoAccount;
import com.tencoding.blog.dto.OAuthToken;
import com.tencoding.blog.model.User;
import com.tencoding.blog.service.UserService;

@Controller
public class UserController {
	
	
	@Value("${tenco.key}")
	private String tencoKey;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	// 로그인 화면
	@GetMapping("/auth/login_form")
	public String login() {
		return "user/login";
	}
	
	// 회원가입 화면
	@GetMapping("auth/join_form")
	public String join() {
		return "/user/join";
	}
	
	@GetMapping("/user/update_form")
	public String updateForm() {
		return "/user/update";
	}
	
	// 글작성 화면
	@GetMapping("/board")
	public String boardSave() {
		return "/board/save_form";
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(@RequestParam String code) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		params.add("grant_type", "authorization_code");
		params.add("client_id", "6e5416444d68dedcde4e87e02be81575");
		params.add("redirect_uri", "http://localhost:9090/auth/kakao/callback");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class);
		
		OAuthToken oAuthToken = null;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		
		RestTemplate rt2 = new RestTemplate();
		HttpHeaders headers2 = new HttpHeaders();
		
		headers2.add("Authorization", "Bearer " + oAuthToken.getAccessToken());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2); 
		
		ResponseEntity<KakaoProfile> kakaoProfileResponse = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoProfileRequest, KakaoProfile.class);
		KakaoAccount account = kakaoProfileResponse.getBody().getKakaoAccount();
		
		User kakaoUser = User.builder()
				.username(account.getEmail() + "_" + kakaoProfileResponse.getBody().getId())
				.password(tencoKey)
				.email(account.getEmail())
				.oauth("kakao")
				.build();
		
		System.out.println(kakaoUser);
		
		User originUser = userService.searchUser(kakaoUser.getUsername());
		if(originUser.getUsername() == null) {
			System.out.println("신규 회원이 아니기 때문에 회원가입을 진행");
			userService.join(kakaoUser);
		}
		// 자동 로그인 처리
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), tencoKey)); //인증 객체 만들어냄
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}
	
	
	
}
