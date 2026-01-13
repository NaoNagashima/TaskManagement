package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.RegisterRequest;
import com.example.taskmanagement.entity.Account;
import com.example.taskmanagement.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegisterController {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute RegisterRequest registration){

        Account account = new Account(registration.getUsername(), passwordEncoder.encode(registration.getPassword()), "User");

        accountRepository.save(account);
        return "login";
    }
}
