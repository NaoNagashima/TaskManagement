package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.RegisterRequest;
import com.example.taskmanagement.entity.Account;
import com.example.taskmanagement.repository.AccountRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;


@Controller
public class RegisterController {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute RegisterRequest registration, RedirectAttributes redirectAttributes){
        String errorUsername = registration.checkUsername(accountRepository);
        String errorPassword = registration.checkPassword();

        if (!"Success".equals(errorUsername)){
            redirectAttributes.addFlashAttribute("error", errorUsername);
            return "redirect:/register";
        } else if (!"Success".equals(errorPassword)){
            redirectAttributes.addFlashAttribute("error", errorPassword);
            return "redirect:/register";
        } else {
            try {
                Account account = new Account(registration.getUsername(), passwordEncoder.encode(registration.getPassword()), "USER");
                accountRepository.save(account);
                redirectAttributes.addFlashAttribute("success", "Account created successfully");
                return "redirect:/login";
            } catch (IllegalStateException e) {
                redirectAttributes.addFlashAttribute("error", e.getMessage());
                return "redirect:/register";
            }

        }
    }
}
