package com.example.taskmanagement.repository;

import com.example.taskmanagement.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    Account findById(long id);
}
