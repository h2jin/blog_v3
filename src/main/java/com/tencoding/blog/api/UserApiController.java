package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.model.User;
import com.tencoding.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	// 회원가입
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> join(@RequestBody User user) {
		// 서비스
		userService.join(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	/*
	//로그인
	@PostMapping("/auth/loginProc")
	public ResponseDto<Integer> login(@RequestBody User user) {
		// 서비스 요청
		
		return new ResponseDto<Integer>(HttpStatus.OK.value() , 1);
	}
	*/
	
	

}
