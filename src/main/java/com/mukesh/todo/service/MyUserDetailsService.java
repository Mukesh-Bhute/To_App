package com.mukesh.todo.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mukesh.todo.Repo.UserRepo;
import com.mukesh.todo.entities.User;

import jakarta.transaction.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return this.userRepo.findByEmail(username)
					.map(user ->{
						
						return new User(
								user.getEmail(),
								user.getPassword(),
								user.getRoles().stream()
									.map(role-> new SimpleGrantedAuthority(role))
									.collect(Collectors.toList() )
									);
					}).orElseThrow( () -> {
						throw new UsernameNotFoundException("User not found");
					});
		
	}
	
	

}
