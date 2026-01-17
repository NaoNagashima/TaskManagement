package com.example.taskmanagement.controller;

import com.example.taskmanagement.entity.Account;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.repository.AccountRepository;
import com.example.taskmanagement.repository.TaskRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;
    private final AccountRepository accountRepository;

    public TaskController(TaskRepository taskRepository, AccountRepository accountRepository) {
        this.taskRepository = taskRepository;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/task")
    public String createTask(@ModelAttribute Task task, Principal principal) {
        Account account = accountRepository.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        String statusMessage = validTask(task.getTitle(), task.getTaskDate(),task.getStartTime(), task.getEndTime());
        if (!"Success".equals(statusMessage)) {
            return "redirect:/?error="+statusMessage;
        }
        task.setOwner(account);
        taskRepository.save(task);

        return "redirect:/";
    }

    @DeleteMapping("/task/{id}")
    public String deleteTask(@PathVariable Long id, Principal principal) {
        System.out.println("Delete Task");
        Task task = taskRepository.findById(id).orElseThrow(null);
        Account account =  accountRepository.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        if (task == null){
            return "redirect:/?error=Task not found";
        }
        if (task.getAccount().getUsername().equals(principal.getName()) || account.getRole().equals("USER")) {
            taskRepository.delete(task);
        }
        return "redirect:/";
    }

    private String validTask(String title, LocalDate taskDate, LocalTime startTime, LocalTime endTime) {
        if (endTime.isBefore(startTime))
            return "Invalid Times: End Time cannot be less than Start Time";
        else if (taskRepository.findByTitle(title).isPresent()){
            return "Invalid Title: already exists";
        } else if (!taskRepository.findOverlappingTasks(taskDate, startTime, endTime).isEmpty()) {
            return "Invalid Overlapping Tasks: Task already exists";
        }
        return "Success";
    }
}
