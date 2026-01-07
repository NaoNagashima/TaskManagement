package com.example.taskmanagement.entity;

import com.example.taskmanagement.repository.AccountRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private String userid;
    private String taskid;
    private String firstName;
    private String lastName;
    private LocalDate taskDate;
    private LocalDateTime taskStartTime;
    private LocalDateTime taskEndTime;

    protected Task() {};

    public Task(long id) {

    }
}
