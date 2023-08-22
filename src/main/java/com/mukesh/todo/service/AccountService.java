package com.mukesh.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mukesh.todo.Repo.AccountRepo;
import com.mukesh.todo.dtos.AccountDto;
import com.mukesh.todo.entities.Account;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepo accountRepo;
	
	public List<Account> getAll() {
		List<Account> accounts = this.accountRepo.findAll();
		return accounts;
	}
	
	public Account create(AccountDto accountDto) {
		
		Account account = new Account();
		
		if(accountDto.getAccount()!= null) {
			account.setAccount(accountDto.getAccount());
		}
		
		if(accountDto.getUsername()!= null) {
			account.setUsername(accountDto.getUsername());
		}
		
		if(accountDto.getPassword()!= null) {
			account.setPassword(accountDto.getPassword());
		}
		
		this.accountRepo.save(account);
		
		return account;
	}
	
	
	public Account getById(Integer id) {
		Account account = this.accountRepo.findById(id).orElse(null);
		
		if(account== null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Account not found");
		}else {
			return account;
		}
	}
	
	public void deleteById(Integer id) {
		Account account = this.getById(id);
		
		if(account== null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
		}else {
			this.accountRepo.delete(account);
		}
	}
	
	
	public Account update(Integer id , AccountDto accountDto) {
		Account account = this.getById(id);
		
		if(accountDto.getAccount()!= null) {
			account.setAccount(accountDto.getAccount());
		}
		
		if(accountDto.getUsername()!= null) {
			account.setUsername(accountDto.getUsername());
		}
		
		if(accountDto.getPassword()!= null) {
			account.setPassword(accountDto.getPassword());
		}
		
		this.accountRepo.save(account);
		
		return account;
	}

}
