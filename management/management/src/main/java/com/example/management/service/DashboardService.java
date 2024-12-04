package com.example.management.service;

import com.example.management.model.Project;
import com.example.management.model.Task;
import com.example.management.repository.ProjectRepository;
import com.example.management.repository.TaskRepository;
import com.example.management.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;


    public long getTotalProjects() {
        return projectRepository.count();
    }


    public Map<String, Long> getTasksByStatus() {
        List<Task> tasks = taskRepository.findAll();
        Map<String, Long> tasksByStatus = new HashMap<>();

        tasksByStatus.put("TO_DO", tasks.stream().filter(task -> task.getStatus() == Status.TO_DO).count());
        tasksByStatus.put("IN_PROGRESS", tasks.stream().filter(task -> task.getStatus() == Status.IN_PROGRESS).count());
        tasksByStatus.put("COMPLETED", tasks.stream().filter(task -> task.getStatus() == Status.COMPLETED).count());

        return tasksByStatus;
    }


    public List<String> getUpcomingProjectDeadlines() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .filter(project -> project.getDeadline() != null && project.getDeadline().isAfter(LocalDateTime.now()))
                .map(project -> "Project: " + project.getName() + " - Due Date: " + project.getDeadline())
                .limit(5) // Return only the first 5 upcoming projects
                .collect(Collectors.toList());
    }


    public ResponseEntity<Map<String, Object>> getDashboardOverview() {
        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("totalProjects", getTotalProjects());
        dashboard.put("tasksByStatus", getTasksByStatus());
        dashboard.put("upcomingDeadlines", getUpcomingProjectDeadlines());

        return ResponseEntity.ok(dashboard);
    }
}
