package com.example.taskmanagement.controller;

import com.example.taskmanagement.repository.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AccountRepository accountRepository;

    public HomeController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("users", accountRepository.findAll());
        return "index";
    }
}
