package com.mukesh.todo.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AddToDoDto {

	private String Title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private String status;

}
