package com.example.management.service;

import com.example.management.model.Project;
import com.example.management.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        if (projects.isEmpty()) {
            throw new RuntimeException("No projects available.");
        }
        return projects;
    }

    public Project getProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new RuntimeException("Project with id " + id + " not found.");
        }
        return project.get();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project updatedProject) {
        Optional<Project> existingProject = projectRepository.findById(id);
        if (existingProject.isEmpty()) {
            throw new RuntimeException("Project with id " + id + " not found.");
        }
        updatedProject.setId(id);
        return projectRepository.save(updatedProject);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project with id " + id + " not found.");
        }
        projectRepository.deleteById(id);
    }
}
