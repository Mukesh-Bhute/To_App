package com.mukesh.todo.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ToDo {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Integer id;
	private String Title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private String status;

}
