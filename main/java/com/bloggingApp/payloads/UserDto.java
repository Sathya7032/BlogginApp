package com.bloggingApp.payloads;

import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min = 4, message = "USER NAME MUST BE ABOVE 4 LETTERS")
	private String fullName;

	@NotEmpty
	@Size(min = 4, message = "USER NAME MUST BE ABOVE 4 LETTERS username already exists")
	@Column(name = "user_name",unique=true)
	private String userName;

	@NotEmpty
	@Email(message="email should not be empty")
	@Column(unique = true)
	private String email;

	@NotEmpty
	@Size(min = 4, max = 10, message = "password should be between 4 and 10 characters")
	private String password;
	
	private List<RoleDto> role = new ArrayList<>();
	
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
}
