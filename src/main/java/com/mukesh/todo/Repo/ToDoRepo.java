package com.mukesh.todo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mukesh.todo.entities.ToDo;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Integer> {

}
