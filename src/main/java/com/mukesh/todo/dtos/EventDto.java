package com.mukesh.todo.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EventDto {

	
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
}
