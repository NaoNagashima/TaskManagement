package com.example.taskmanagement.repository;

import com.example.taskmanagement.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByUsername(String username);

    Account findById(long id);
}
