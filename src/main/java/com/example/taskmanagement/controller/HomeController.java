package com.example.taskmanagement.controller;

import com.example.taskmanagement.entity.Account;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.repository.AccountRepository;
import com.example.taskmanagement.repository.TaskRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final AccountRepository accountRepository;
    private final TaskRepository taskRepository;

    public HomeController(AccountRepository accountRepository,  TaskRepository taskRepository) {
        this.accountRepository = accountRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        Account account = accountRepository.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        List<Task> tasks = taskRepository.findByOwner(account);
        model.addAttribute("tasks", tasks);
        model.addAttribute("users", accountRepository.findAll());
        model.addAttribute("task", new Task());
        return "index";
    }
}
