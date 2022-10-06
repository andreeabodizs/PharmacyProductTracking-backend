package com.product.tracking.dto;

import org.springframework.beans.BeanUtils;

import com.product.tracking.entities.User;

public class UserDtoForUpdate {

	private Long userId;

	private String email;

	private String username;

	private String password;
	
	private String newPassword;


	public UserDtoForUpdate() {

	}
	
	public UserDtoForUpdate(User user) {
		BeanUtils.copyProperties(user, this, "creationDate", "password");
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	




}
