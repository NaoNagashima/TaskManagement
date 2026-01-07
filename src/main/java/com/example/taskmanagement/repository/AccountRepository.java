package com.example.taskmanagement.repository;

import com.example.taskmanagement.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findByLastName(String lastName);

    Account findById(long id);
}
