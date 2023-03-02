package com.taskmanager.auriga.controller;

import com.taskmanager.auriga.model.Task;
import com.taskmanager.auriga.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/task")
    public List<Task> getTask() {

        return taskService.getTasks();
    }

    @PostMapping("/task")
    public Task addTask(@RequestBody Task task) {

        return taskService.save(task);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task taskParam, @PathVariable Long id) {

        if(taskService.existById(id)) {
            Task task = taskService.getTaskById(id)
                    .orElseThrow(()->new EntityNotFoundException("Requested task not found"));

            task.setTitle(taskParam.getTitle());
            task.setDueDate(taskParam.getDueDate());
            task.setType(taskParam.getType());
            task.setDescription(taskParam.getDescription());

            taskService.save(task);

            return ResponseEntity.ok().body(task);
        } else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + "task not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
