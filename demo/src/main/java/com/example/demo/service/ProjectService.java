package com.example.demo.service;

import com.example.demo.Mapper.Mapper;
import com.example.demo.dto.AllProjectDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.entities.Project;
import com.example.demo.entities.Tag;
import com.example.demo.exception.NoEntitiesException;
import com.example.demo.exception.ValidationTagException;
import com.example.demo.repositories.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeService.class);

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ValidationService validationService;

    public void addProject(Project project) {
        projectRepository.save(project);
        logger.info("Add Project " + project.toString());
    }

    public Project getProject(Long id) throws NoEntitiesException {
        return projectRepository.findById(id).orElseThrow(() -> new NoEntitiesException("Project not found"));
    }

    public AllProjectDto getProjectDto(Long id) throws NoEntitiesException {
        return Mapper.getProjectDto(getProject(id));
    }

    public List<?> getProjectList() {
        return Mapper.getDtoList(projectRepository.findAll(), ProjectDto.class);
    }

    public void deleteProject(Long id) throws NoEntitiesException {
        projectRepository.delete(getProject(id));
        logger.info("Delete Project - " + id);

    }

    public void addTagInProject(Long id_project, String name) throws NoEntitiesException, ValidationTagException {
        Project project = getProject(id_project);
        validationService.validate(name, project);
        project.addTag(new Tag(name));
        projectRepository.save(project);
        logger.info(String.format("Added a tag - %s to the Project - %s", name, project.toString()));
    }

    public void setValidationInProject(Long id_project, String name) throws NoEntitiesException {
        Project project = getProject(id_project);
        project.setValidation(name);
        projectRepository.save(project);
        logger.info(String.format("Set validation in Project id - %s", project.toString()));
    }

    public void deleteValidation(Long id_project) throws NoEntitiesException {
        Project project = getProject(id_project);
        project.resetValidationToDefault();
        projectRepository.save(project);
        logger.info(String.format("Removed verification Project - %s", project.toString()));

    }

}
