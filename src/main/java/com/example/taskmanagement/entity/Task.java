package com.example.taskmanagement.entity;

import com.example.taskmanagement.repository.AccountRepository;
import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private LocalDate taskDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account owner;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {return this.title;}

    public void setTitle(String title) {this.title = title;}

    public LocalDate getTaskDate() {return this.taskDate;}

    public void setTaskDate(LocalDate taskDate) {this.taskDate = taskDate;}

    public LocalTime getStartTime() {return this.startTime;}

    public void setStartTime(LocalTime startTime) {this.startTime = startTime;}

    public LocalTime getEndTime() {return this.endTime;}

    public void setEndTime(LocalTime endTime) {this.endTime = endTime;}

    public void setOwner(Account owner) {this.owner = owner;}

    public Account getAccount(){
        return this.owner;
    }
}
