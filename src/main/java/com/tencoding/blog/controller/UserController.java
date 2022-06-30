package com.tencoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
//	@Autowired
//	AuthenticationManager authenticationManager;
//	
//	@Autowired
//	private HttpSession httpSession;
	
	//홈화면
	@GetMapping({"", "/"})
	public String index() {
		
		return "index";
	}
	
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
	
}
