package com.tencoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
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
	
	// 글작성 화면
	@GetMapping("/board")
	public String boardSave() {
		return "/board/save_form";
	}
	
	
	
}
