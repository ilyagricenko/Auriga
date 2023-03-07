package com.taskmanager.auriga.controller;

import com.taskmanager.auriga.dto.CountType;
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

    @GetMapping("/task/vData/percentage")
    public List<CountType> getGroupByType() {

        return taskService.getGroupByType();
    }

    @GetMapping("/task")
    public List<Task> getTask() {

        return taskService.getTasks();
    }

    @PostMapping("/task")
    public Task addTask(@RequestBody Task task) {

        return taskService.save(task);
    }

    @GetMapping("/task/{id}")
    public Task getById(@PathVariable Long id) {

        return taskService.getTaskById(id)
                .orElseThrow(()->new EntityNotFoundException("Requested task not found"));
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
            message.put("message", id + " task not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {

        if(taskService.existById(id)) {

            taskService.delete(id);

            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + " task was deleted");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + "task not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
