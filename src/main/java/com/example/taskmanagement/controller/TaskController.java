package com.example.taskmanagement.controller;

import com.example.taskmanagement.entity.Account;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.repository.AccountRepository;
import com.example.taskmanagement.repository.TaskRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;
    private final AccountRepository accountRepository;

    public TaskController(TaskRepository taskRepository, AccountRepository accountRepository) {
        this.taskRepository = taskRepository;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/task")
    public String createTask(@ModelAttribute Task task, Principal principal, Model model) {
        Account account = accountRepository.findByUsername(principal.getName());
        task.setOwner(account);
        taskRepository.save(task);

        return "redirect:/";
    }

}
