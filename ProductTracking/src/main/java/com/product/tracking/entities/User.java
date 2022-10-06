package com.product.tracking.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.product.tracking.dto.UserDto;
import com.product.tracking.dto.UserDtoForUpdate;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@NotBlank(message = "Email cannot be empty!")
	@Column(unique = true, nullable = false)
	private String email;

	@NotBlank(message = "Username cannot be empty!")
	@Column(unique = true, nullable = false)
	@Size(min = 5, max = 50)
	private String username;

	
	@NotBlank(message = "Password cannot be empty!")
	@Column(nullable = false)
	private String password;

	@NotNull(message = "Creation Date cannot be empty!")
	@Column(nullable = false)
	private LocalDateTime creationDate;

	public User() {}
	
	public User(UserDto userDto, String password) {
		BeanUtils.copyProperties(userDto, this);
		this.password = password;
	}
	
	public User(UserDtoForUpdate userDtoForUpdate, LocalDateTime creationDate, String password) {
		BeanUtils.copyProperties(userDtoForUpdate, this, "password");
		this.creationDate = creationDate;
		this.password = password;
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

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

}
