package com.product.tracking.services;

import java.util.List;

import javax.security.auth.login.AccountException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.tracking.dao.UserRepository;
import com.product.tracking.dto.UserDtoForUpdate;
import com.product.tracking.entities.User;
import com.product.tracking.exceptions.AccountAlreadyExistsException;
import com.product.tracking.exceptions.OldPasswordIsIncorectException;
import com.product.tracking.exceptions.UsernameAlreadyExistsException;

@Service
@Transactional
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) throws AccountAlreadyExistsException, UsernameAlreadyExistsException {
		final String email = user.getEmail();
		final String username = user.getUsername();
		if (userRepository.existsByEmail(email)) {
			throw new AccountAlreadyExistsException(email);
		} else if (userRepository.existsByUsername(username)) {
			throw new UsernameAlreadyExistsException(username);
		} else {
			String rawPassword = user.getPassword();
			user.setPassword(passwordEncoder.encode(rawPassword));
			return userRepository.save(user);
		}

	}

	public User changePassword(UserDtoForUpdate userDtoForUpdate) throws OldPasswordIsIncorectException {
		String username = userDtoForUpdate.getUsername();
		User databaseUser = userRepository.findById(userDtoForUpdate.getUserId()).get();
		String oldPassword = databaseUser.getPassword();
		String oldUsername = databaseUser.getUsername();

		if (!passwordEncoder.matches(userDtoForUpdate.getPassword(), oldPassword)) {
			throw new OldPasswordIsIncorectException();
		} else {

			String rawPassword = userDtoForUpdate.getNewPassword();
			String encodedPassword = passwordEncoder.encode(rawPassword);
			User updatedUser = new User(userDtoForUpdate, databaseUser.getCreationDate(), encodedPassword);
			return userRepository.save(updatedUser);
		}

	}

	public User updateUsername(UserDtoForUpdate userDtoForUpdate) throws UsernameAlreadyExistsException {
		String username = userDtoForUpdate.getUsername();
		User databaseUser = userRepository.findById(userDtoForUpdate.getUserId()).get();
		String encodedPassword = databaseUser.getPassword();
		String oldUsername = databaseUser.getUsername();
		
		if (!username.equals(oldUsername) && userRepository.existsByUsername(username)) {
			throw new UsernameAlreadyExistsException(username);
		} else {
			User updatedUser = new User(userDtoForUpdate, databaseUser.getCreationDate(), encodedPassword);
			return userRepository.save(updatedUser);
		}
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).get();
	}

}
