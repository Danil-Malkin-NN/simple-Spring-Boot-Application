package com.example.demo.service;

import com.example.demo.Mapper.Mapper;
import com.example.demo.dto.ProjectNameTagDto;
import com.example.demo.entities.Project;
import com.example.demo.entities.Tag;
import com.example.demo.exception.NoEntitiesException;
import com.example.demo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TagService tagService;

    public void addProject(Project project) {
        projectRepository.save(project);
    }

    public Project getProject(Long id) throws NoEntitiesException {
        return projectRepository.findById(id).orElseThrow(() -> new NoEntitiesException("Project not found"));
    }

    public ProjectNameTagDto getProjectDto(Long id) throws NoEntitiesException {
        return Mapper.getProjectDto(getProject(id));
    }

    public List<?> getProjectList() {
        return Mapper.getDtoList(projectRepository.findAll(), ProjectNameTagDto.class);
    }

    public void deleteProject(Long id) throws NoEntitiesException {
        projectRepository.delete(getProject(id));
    }

    public void addTagInProject(Long id_project, String name) throws NoEntitiesException {
        Project project = getProject(id_project);
        project.addTag(new Tag(name));
        projectRepository.save(project);
    }
}
