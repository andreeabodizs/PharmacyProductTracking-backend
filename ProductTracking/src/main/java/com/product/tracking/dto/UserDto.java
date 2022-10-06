package com.product.tracking.dto;

import org.springframework.beans.BeanUtils;

import com.product.tracking.entities.User;

public class UserDto {

	private Long userId;
	
	private String email;
	
	private String username;
		

	public UserDto( ) {
		
	}
	
	public UserDto(User user) {
		BeanUtils.copyProperties(user, this, "password");
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
