package com.mukesh.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mukesh.todo.dtos.EventDto;
import com.mukesh.todo.entities.Event;
import com.mukesh.todo.service.EventService;

@Controller
public class EventController {

	
	@Autowired
	private EventService eventService;

	@GetMapping("/remember")
	public ModelAndView landing() {
		ModelAndView modelAndView = new ModelAndView("toRemember");
		return modelAndView;
	}
	
	@GetMapping("/event")
	public ModelAndView findAllEvents(){
		ModelAndView modelAndView = new ModelAndView("event");
		modelAndView.addObject("events",this.eventService.getall());
		
		return modelAndView;
	}
	
	@GetMapping("/event/entry")
	public String create(Model model) {
		model.addAttribute("eventform", new Event());
		
		return "addEvent";
		
	}
	
	@PostMapping("/event/add")
	public String add(Model model , @ModelAttribute EventDto eventDto) {
		model.addAttribute("eventform", new Event());
		this.eventService.create(eventDto);
		return "redirect:/event";
	}
	
	
	@GetMapping("/event/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.eventService.deleteById(id);
		return "redirect:/event";
	}
	
	@GetMapping("/event/edit/{id}")
	public String edit(Model model,  @PathVariable Integer id ) {
		model.addAttribute("editEvent", this.eventService.getById(id));
		return "editEvent";
	}
	
	@PostMapping("/event/update/{id}")
	public String update( @ModelAttribute EventDto eventDto , @PathVariable Integer id) {
		this.eventService.update(id, eventDto);
		return "redirect:/event";
	}
}
