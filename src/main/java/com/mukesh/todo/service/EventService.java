package com.mukesh.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mukesh.todo.Repo.EventRepo;
import com.mukesh.todo.dtos.EventDto;
import com.mukesh.todo.entities.Event;

@Service
public class EventService {
	
	@Autowired
	private EventRepo eventRepo;
	

	public Event create( EventDto eventDto) {
		Event event = new Event();
		
		event.setName(eventDto.getName());
		event.setDate(eventDto.getDate());
		
		
		this.eventRepo.save(event);
		return event;
	}
	
	public List<Event> getall() {
	List<Event> events = this.eventRepo.findAll();
		return events;
	}
	
	public void deleteById( Integer id) {
		Event event = this.eventRepo.findById(id).orElse(null);
		
		if(event == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND , "ToDo not found");
		}else {
			this.eventRepo.delete(event);
		}
	}
	
	
	public Event getById( Integer id) {
		Event event = this.eventRepo.findById(id).orElse(null);
		
		if(event == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND , "ToDo not found");
		}else {
			return event;
		}
	}
	
	public Event update( Integer id, EventDto eventDto ) {
	
		Event event = this.getById(id);
		
		if(eventDto.getName()!= null) {
			event.setName(eventDto.getName());
			
		}
		
		if(eventDto.getDate()!= null) {
			event.setDate(eventDto.getDate());
		}
		
		this.eventRepo.save(event);
		
		return event;
	}
	
	

}
