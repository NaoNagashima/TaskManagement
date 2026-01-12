package com.example.taskmanagement.security;

import com.example.taskmanagement.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AccountUserDetails implements UserDetails {

    private final Account account;

    AccountUserDetails(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return getAccount().getUsername();
    }
}
