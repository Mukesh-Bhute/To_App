package com.mukesh.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mukesh.todo.dtos.AccountDto;
import com.mukesh.todo.entities.Account;
import com.mukesh.todo.entities.Event;
import com.mukesh.todo.service.AccountService;

@Controller
public class AccountController {
	
	
	@Autowired
	private AccountService accountService;
	
	
	@GetMapping("/account")
	public ModelAndView findAllAcccount(){
		ModelAndView modelAndView = new ModelAndView("account");
		modelAndView.addObject("accounts",this.accountService.getAll());
		
		return modelAndView;
	}
	
	@GetMapping("/account/entry")
	public String create(Model model) {
		model.addAttribute("accountform", new Account());
		
		return "addAccount";
		
	}
	
	@PostMapping("/account/add")
	public String add(Model model , @ModelAttribute AccountDto accountDto) {
		model.addAttribute("accountform", new Event());
		this.accountService.create(accountDto);
		return "redirect:/account";
	}
	
	
	@GetMapping("/account/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.accountService.deleteById(id);
		return "redirect:/account";
	}
	
	@GetMapping("/account/edit/{id}")
	public String edit(Model model,  @PathVariable Integer id ) {
		model.addAttribute("editAccount", this.accountService.getById(id));
		return "editAccount";
	}
	
	@PostMapping("/account/update/{id}")
	public String update( @ModelAttribute AccountDto accountDto, @PathVariable Integer id) {
		this.accountService.update(id, accountDto);
		return "redirect:/account";
	}
	
	

}
