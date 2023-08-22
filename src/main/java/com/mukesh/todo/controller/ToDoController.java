package com.mukesh.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mukesh.todo.dtos.AddToDoDto;
import com.mukesh.todo.entities.ToDo;
import com.mukesh.todo.service.ToDoService;

@Controller
public class ToDoController {

	@Autowired
	private ToDoService toDoService;
	
	@GetMapping("/")
	public ModelAndView landing(){
		ModelAndView modelAndView = new ModelAndView("main");
		
		
		return modelAndView;
	}
	
	@GetMapping("/about")
	public ModelAndView about(){
		ModelAndView modelAndView = new ModelAndView("about");
		
		
		return modelAndView;
	}
	
	@GetMapping("/todo")
	public ModelAndView findAllToDo(){
		ModelAndView modelAndView = new ModelAndView("todo");
		modelAndView.addObject("todos",this.toDoService.getall());
		
		return modelAndView;
	}
	
	@GetMapping("/todo/entry")
	public String create(Model model) {
		model.addAttribute("todoform", new ToDo());
		
		return "addToDo";
		
	}
	
	@PostMapping("/todo/add")
	public String add(Model model , @ModelAttribute AddToDoDto addToDoDto) {
		model.addAttribute("todoform", new ToDo());
		this.toDoService.create(addToDoDto);
		return "redirect:/todo";
	}
	
	
	@GetMapping("/todo/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.toDoService.deleteById(id);
		return "redirect:/todo";
	}
	
	@GetMapping("/todo/edit/{id}")
	public String edit(Model model,  @PathVariable Integer id ) {
		model.addAttribute("editToDo", this.toDoService.getById(id));
		return "editToDo";
	}
	
	@PostMapping("/todo/update/{id}")
	public String update( @ModelAttribute AddToDoDto addToDoDto , @PathVariable Integer id) {
		this.toDoService.update(id, addToDoDto);
		return "redirect:/todo";
	}
	
	@GetMapping("/todo/mark/{id}")
	public String markComplete( @PathVariable Integer id) {
		this.toDoService.mark(id );
		return "redirect:/todo";
	}
	
}
