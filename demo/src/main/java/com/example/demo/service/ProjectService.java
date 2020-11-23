package com.example.demo.service;

import com.example.demo.entities.Project;
import com.example.demo.exception.NoEntitiesException;
import com.example.demo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;


    public void addProject(Project project) {
        projectRepository.save(project);
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).get();
    }

    public List<Project> getProjectList() {
        return projectRepository.findAll();
    }

    public void deleteProject(Long id) throws NoEntitiesException {
        if(projectRepository.existsById(id)){
            projectRepository.deleteById(id);
        }else{
            throw new NoEntitiesException("project not found");
        }

    }

}
