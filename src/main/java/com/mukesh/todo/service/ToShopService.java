package com.mukesh.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mukesh.todo.Repo.ToShopRepo;
import com.mukesh.todo.dtos.ToShopDto;
import com.mukesh.todo.entities.ToShop;

@Service
public class ToShopService {

	@Autowired
	private ToShopRepo toShopRepo;
	

	public ToShop create( ToShopDto toShopDto) {
		ToShop toShop = new ToShop();
		
		toShop.setName(toShopDto.getName());
		toShop.setCount(toShopDto.getCount());
		toShop.setUnit(toShopDto.getUnit());
		
		this.toShopRepo.save(toShop);
		return toShop;
	}
	
	public List<ToShop> getall() {
	List<ToShop> toShops =	this.toShopRepo.findAll();
		return toShops;
	}
	
	public void deleteById( Integer id) {
		ToShop toShop  = this.toShopRepo.findById(id).orElse(null);
		
		if(toShop == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND , "ToDo not found");
		}else {
			this.toShopRepo.delete(toShop);
		}
	}
	
	
	public ToShop getById( Integer id) {
		ToShop toShop = this.toShopRepo.findById(id).orElse(null);
		
		if(toShop == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND , "ToDo not found");
		}else {
			return toShop;
		}
	}
	
	public ToShop update( Integer id, ToShopDto toShopDto ) {
	
		ToShop toShop = this.getById(id);
		
		if(toShopDto.getName()!= null) {
			toShop.setName(toShopDto.getName());
			
		}
		
		if(toShopDto.getCount()!= null) {
			toShop.setCount(toShopDto.getCount());
		}
		
		if(toShopDto.getUnit()!= null) {
			toShop.setUnit(toShopDto.getUnit());
		}
		
		this.toShopRepo.save(toShop);
		
		return toShop;
	}
	
	public void deleteAll( ) {
		
			this.toShopRepo.deleteAll();;
		
	}
	

}
