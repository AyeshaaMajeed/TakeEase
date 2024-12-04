package com.example.management.repository;

import com.example.management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedUserId(Long assignedUserId);
    List<Task> findByProjectId(Long projectId);
}
