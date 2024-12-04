package com.example.management.service;

import com.example.management.ResourceNotFoundException;
import com.example.management.model.Task;
import com.example.management.repository.TaskRepository;
import com.example.management.enums.Status;
import com.example.management.enums.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public List<Task> getTasksForUser(Long userId) {
        return taskRepository.findByAssignedUserId(userId);
    }

    public List<Task> getTasksForProject(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    public Task updateTaskStatus(Long taskId, String status) {
        Task task = getTaskById(taskId);

        try {

            Status newStatus = Status.valueOf(status.toUpperCase());
            task.setStatus(newStatus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + status);
        }

        return taskRepository.save(task);
    }


    public Task updateTaskPriority(Long taskId, String priority) {
        Task task = getTaskById(taskId);

        try {

            Priority newPriority = Priority.valueOf(priority.toUpperCase());
            task.setPriority(newPriority);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid priority: " + priority);
        }

        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new ResourceNotFoundException("Task with id " + taskId + " not found.");
        }
        taskRepository.deleteById(taskId);
    }
}
