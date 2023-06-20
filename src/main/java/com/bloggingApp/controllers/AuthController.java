package com.bloggingApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingApp.entities.User;
import com.bloggingApp.exceptions.ApiException;
import com.bloggingApp.payloads.JwtAuthRequest;
import com.bloggingApp.payloads.JwtAuthResponse;
import com.bloggingApp.payloads.UserDto;
import com.bloggingApp.security.JwtTokenHelper;
import com.bloggingApp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/auth/")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper mapper;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authencate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		
		
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUser(this.mapper.map((User)userDetails,UserDto.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authencate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticateToken = new UsernamePasswordAuthenticationToken(username,
				password);
		try {
			this.authenticationManager.authenticate(authenticateToken);
		} catch (BadCredentialsException e) {
			System.out.println("Invalid details ");
			throw new ApiException("Invalid username or pasword");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
		UserDto registerUser = this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registerUser, HttpStatus.CREATED);
	}

}
