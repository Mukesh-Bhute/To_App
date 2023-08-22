package com.mukesh.todo.dtos;

import java.util.List;

import lombok.Data;
@Data
public class AddUserDto {
	
	private String name;
	private String email;
	private String password;
	private List<String> roles;

}
