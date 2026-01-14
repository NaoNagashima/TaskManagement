package com.example.taskmanagement.dto;

import com.example.taskmanagement.entity.Account;
import com.example.taskmanagement.repository.AccountRepository;

public class RegisterRequest{
    private String username;
    private String password;

    public RegisterRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String checkUsername(AccountRepository accountRepository){
        boolean userExists = accountRepository.findByUsername(this.username).isPresent();
        if (userExists) return "Username Already Exists";
        return "Success";
    }

    public String checkPassword(){
        if (this.password.length() < 6) {
            return "Error: Password too short";
        }
        return "Success";
    }
}
