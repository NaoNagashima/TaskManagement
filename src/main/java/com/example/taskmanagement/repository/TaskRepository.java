package com.example.taskmanagement.repository;

import com.example.taskmanagement.entity.Account;
import com.example.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Task findById(long id);

    Optional<Task> findByTitle(String title);

    List<Task> findByOwner(Account account);

    @Query("SELECT t FROM Task t WHERE t.taskDate = :taskDate AND t.startTime < :endTime AND t.endTime > :startTime")
    List<Task> findOverlappingTasks(@Param("taskDate") LocalDate taskDate, @Param("startTime") LocalTime startTime, @Param("endTime") LocalTime endTime);
}
