package com.example.management.controller;

import com.example.management.model.Task;
import com.example.management.service.TaskService;
import com.example.management.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);
        return ResponseEntity.ok(savedTask);
    }


    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/assigned/{userId}")
    public ResponseEntity<List<Task>> getTasksForUser(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksForUser(userId);
        return ResponseEntity.ok(tasks);
    }


    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksForProject(@PathVariable Long projectId) {
        List<Task> tasks = taskService.getTasksForProject(projectId);
        return ResponseEntity.ok(tasks);
    }


    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        try {
            Task task = taskService.getTaskById(taskId);
            return ResponseEntity.ok(task);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PatchMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestParam String status) {
        try {
            Task updatedTask = taskService.updateTaskStatus(taskId, status);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PatchMapping("/{taskId}/priority")
    public ResponseEntity<Task> updateTaskPriority(@PathVariable Long taskId, @RequestParam String priority) {
        try {
            Task updatedTask = taskService.updateTaskPriority(taskId, priority);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.ok("Task deleted successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with id " + taskId + " not found.");
        }
    }
}
