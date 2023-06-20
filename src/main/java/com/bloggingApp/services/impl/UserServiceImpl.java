package com.bloggingApp.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bloggingApp.entities.User;
import com.bloggingApp.payloads.UserDto;
import com.bloggingApp.repository.RoleRepo;
import com.bloggingApp.repository.UserRepo;
import com.bloggingApp.services.UserService;
import com.bloggingApp.config.AppConstants;
import com.bloggingApp.entities.*;
import com.bloggingApp.exceptions.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto useDto) {
		User user = this.dtoToUser(useDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setFullName(userDto.getFullName());
		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);
	}

	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);

		// user.setId(userDto.getId());
		// user.setFullName(userDto.getFullName());
		// user.setUserName(userDto.getUserName());
		// user.setEmail(userDto.getEmail());
		// user.setPassword(userDto.getPassword());
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);

		// userDto.setId(user.getId());
		// userDto.setFullName(user.getFullName());
		// userDto.setUserName(user.getUserName());
		// userDto.setEmail(user.getEmail());
		// userDto.setPassword(user.getPassword());
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);

		// encode password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRole().add(role);

		User newUser = this.userRepo.save(user);

		return this.modelMapper.map(newUser, UserDto.class);
	}

}
