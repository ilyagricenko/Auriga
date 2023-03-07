package com.taskmanager.auriga.service;

import com.taskmanager.auriga.dto.CountType;
import com.taskmanager.auriga.model.Task;
import com.taskmanager.auriga.repository.TaskRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;

    @Transactional
    public List<Task> getTasks(){

        return taskRepo.getAllTaskDueDateDesc();
    }

    @Transactional
    public Task save(Task task) {

        return taskRepo.saveAndFlush(task);
    }

    @Transactional
    public boolean existById(Long id) {

        return taskRepo.existsById(id);
    }

    @Transactional
    public Optional<Task> getTaskById(Long id) {

        return taskRepo.findById(id);
    }

    public void delete(Long id) {

        taskRepo.deleteById(id);
    }

    public List<CountType> getGroupByType() {

        return taskRepo.getGroupByType();
    }
}
