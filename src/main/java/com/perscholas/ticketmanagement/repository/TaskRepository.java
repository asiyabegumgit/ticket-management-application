package com.perscholas.ticketmanagement.repository;

import com.perscholas.ticketmanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
