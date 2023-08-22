package com.mukesh.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mukesh.todo.Repo.ToDoRepo;
import com.mukesh.todo.dtos.AddToDoDto;
import com.mukesh.todo.entities.ToDo;

@Service
public class ToDoService {
	
	@Autowired
	private ToDoRepo toDoRepo;
	

	public ToDo create( AddToDoDto addToDoDto) {
		ToDo toDo = new ToDo();
		
		toDo.setTitle(addToDoDto.getTitle());
		toDo.setDate(addToDoDto.getDate());
		toDo.setStatus(addToDoDto.getStatus());
		
		this.toDoRepo.save(toDo);
		return toDo;
	}
	
	public List<ToDo> getall() {
	List<ToDo> todos =	this.toDoRepo.findAll();
		return todos;
	}
	
	public void deleteById( Integer id) {
		ToDo toDo = this.toDoRepo.findById(id).orElse(null);
		
		if(toDo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND , "ToDo not found");
		}else {
			this.toDoRepo.delete(toDo);
		}
	}
	
	
	public ToDo getById( Integer id) {
		ToDo toDo = this.toDoRepo.findById(id).orElse(null);
		
		if(toDo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND , "ToDo not found");
		}else {
			return toDo;
		}
	}
	
	public ToDo update( Integer id, AddToDoDto addToDoDto ) {
	
		ToDo toDo = this.getById(id);
		
		if(addToDoDto.getTitle()!= null) {
			toDo.setTitle(addToDoDto.getTitle());
			
		}
		
		if(addToDoDto.getDate()!= null) {
			toDo.setDate(addToDoDto.getDate());
		}
		
		this.toDoRepo.save(toDo);
		
		return toDo;
	}
	
	public ToDo mark(Integer id ) {
		ToDo toDo = this.getById(id);
		
		if(toDo != null) {
			toDo.setStatus("Completed!!!");
		}
		this.toDoRepo.save(toDo);
		
		return toDo;
	}
}
