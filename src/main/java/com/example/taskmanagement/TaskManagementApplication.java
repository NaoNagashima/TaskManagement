package com.example.taskmanagement;

import com.example.taskmanagement.entity.Account;
import com.example.taskmanagement.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskManagementApplication {

    private static final Logger log = LoggerFactory.getLogger(TaskManagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TaskManagementApplication.class, args);
    }
}
