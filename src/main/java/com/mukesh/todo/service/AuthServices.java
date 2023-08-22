package com.mukesh.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mukesh.todo.Repo.UserRepo;
import com.mukesh.todo.dtos.AddUserDto;
import com.mukesh.todo.entities.User;

@Service
public class AuthServices {
	
	@Autowired
	private UserRepo  userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User addUser( AddUserDto addUserDto ) {
		if(this.userRepo.findByEmail(addUserDto.getEmail()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"User already exist");
	}
		
		User user = new User();
		
		user.setName(addUserDto.getName());
		user.setEmail(addUserDto.getEmail());
		user.setPassword(passwordEncoder.encode(addUserDto.getPassword()));
		user.setRoles(addUserDto.getRoles());
		
		this.userRepo.save(user);
		
		return user;
	}

}
