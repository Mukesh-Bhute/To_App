package com.mukesh.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mukesh.todo.dtos.AddUserDto;
import com.mukesh.todo.dtos.LoginDto;
import com.mukesh.todo.entities.User;
import com.mukesh.todo.service.AuthServices;

@Controller
public class AuthController {

	@Autowired
	private AuthServices authServices;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/main")
	public ModelAndView landing(){
		ModelAndView modelAndView = new ModelAndView("index");
		
		return modelAndView;
	}
	
	@GetMapping("/user/reg")
	public String reg(Model model) {
		model.addAttribute("userform", new User());
		return "adduser";
	}
	
	@PostMapping("/user/add")
	public String register(Model model , @ModelAttribute AddUserDto addUserDto) {
		model.addAttribute("userform", new User());
		this.authServices.addUser(addUserDto);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String log() {
		return "login";
	}
	
	@PostMapping("/log")
	public String login( @ModelAttribute LoginDto logindto ) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(logindto.getEmail(), logindto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}
	
}
