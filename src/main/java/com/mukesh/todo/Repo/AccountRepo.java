package com.mukesh.todo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mukesh.todo.entities.Account;

public interface AccountRepo extends JpaRepository<Account, Integer> {

}
