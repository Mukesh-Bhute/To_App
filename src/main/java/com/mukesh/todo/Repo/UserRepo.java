package com.mukesh.todo.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mukesh.todo.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);

}
