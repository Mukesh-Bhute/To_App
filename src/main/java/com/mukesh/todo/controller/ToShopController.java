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
import com.mukesh.todo.dtos.ToShopDto;
import com.mukesh.todo.entities.ToDo;
import com.mukesh.todo.entities.ToShop;
import com.mukesh.todo.service.ToShopService;

@Controller
public class ToShopController {
	
	@Autowired
	private ToShopService toShopService;
	
	
	@GetMapping("/toshop")
	public ModelAndView findAllToShop(){
		ModelAndView modelAndView = new ModelAndView("toshop");
		modelAndView.addObject("toshops",this.toShopService.getall());
		
		return modelAndView;
	}
	
	@GetMapping("/toshop/entry")
	public String create(Model model) {
		model.addAttribute("toshopform", new ToShop());
		
		return "addToShop";
		
	}
	
	@PostMapping("/toshop/add")
	public String add(Model model , @ModelAttribute ToShopDto toShopDto) {
		model.addAttribute("toshopform", new ToDo());
		this.toShopService.create(toShopDto);
		return "redirect:/toshop";
	}
	
	@GetMapping("/toshop/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.toShopService.deleteById(id);
		return "redirect:/toshop";
	}
	
	@GetMapping("/toshop/edit/{id}")
	public String edit(Model model,  @PathVariable Integer id ) {
		model.addAttribute("editToShop", this.toShopService.getById(id));
		return "editToShop";
	}
	
	@PostMapping("/toshop/update/{id}")
	public String update( @ModelAttribute ToShopDto toShopDto , @PathVariable Integer id) {
		this.toShopService.update(id, toShopDto);
		return "redirect:/toshop";
	}

	@GetMapping("/toshop/delete")
	public String delete() {
		this.toShopService.deleteAll();
		return "redirect:/toshop";
	}
}
