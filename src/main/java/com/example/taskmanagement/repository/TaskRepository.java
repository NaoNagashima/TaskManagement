package com.example.taskmanagement.repository;

import com.example.taskmanagement.entity.Account;
import com.example.taskmanagement.entity.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Task findById(long id);

    List<Task> findByOwner(Account account);
}
