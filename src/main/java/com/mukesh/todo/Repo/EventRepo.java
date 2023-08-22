package com.mukesh.todo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mukesh.todo.entities.Event;

public interface EventRepo extends JpaRepository<Event, Integer> {

}
