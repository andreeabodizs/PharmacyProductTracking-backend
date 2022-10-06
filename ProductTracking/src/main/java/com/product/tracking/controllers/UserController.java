package com.product.tracking.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.product.tracking.dto.UserDto;
import com.product.tracking.dto.UserDtoForUpdate;
import com.product.tracking.entities.User;
import com.product.tracking.exceptions.AccountAlreadyExistsException;
import com.product.tracking.exceptions.OldPasswordIsIncorectException;
import com.product.tracking.exceptions.UsernameAlreadyExistsException;
import com.product.tracking.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// get one user by email from database
	@GetMapping(value = "/get-user-by-email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto getUserByEmail(@PathVariable String email) {
		return new UserDto(userService.getUserByEmail(email));
	}

	// create one user in database
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User registerUser(@Valid @RequestBody User user)
			throws AccountAlreadyExistsException, UsernameAlreadyExistsException {
		return userService.createUser(user);
//		
	}

	// change password of one user
	@PutMapping(value = "/change-password", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDtoForUpdate changePassword(@RequestBody UserDtoForUpdate userDtoForUpdate)
			throws  OldPasswordIsIncorectException {
		return new UserDtoForUpdate(userService.changePassword(userDtoForUpdate));
	}

	// change username of one user
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDtoForUpdate updateUser(@RequestBody UserDtoForUpdate userDtoForUpdate)
			throws UsernameAlreadyExistsException {
		return new UserDtoForUpdate(userService.updateUsername(userDtoForUpdate));
	}
}
