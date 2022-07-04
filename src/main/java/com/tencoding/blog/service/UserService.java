package com.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	// 회원가입 서비스
	public int join(User user) {
		try {
			String rawPassword = user.getPassword();
			String encoPassword = encoder.encode(rawPassword);
			user.setPassword(encoPassword);
			user.setRoleType(RoleType.USER);
			userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	@Transactional(readOnly = true)
	public User searchUser(String username) {
		User userEnctity = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
		return userEnctity;
	}
	
	@Transactional
	public void updateUser(User user) {
		User userEntity = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원정보가 없습니다.");
		});
		
		String rawPassword = user.getPassword();
		String hashPassword = encoder.encode(rawPassword);
		userEntity.setPassword(hashPassword);
		userEntity.setEmail(user.getEmail());
	}
	
}
