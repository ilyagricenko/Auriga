package com.taskmanager.auriga.service;

import com.taskmanager.auriga.model.Task;
import com.taskmanager.auriga.repository.TaskRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;

    @Transactional
    public List<Task> getTasks(){

        return taskRepo.findAll();
    }

}
