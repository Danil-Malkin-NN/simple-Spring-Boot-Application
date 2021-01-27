package com.example.demo.service;

import com.example.demo.Mapper.Mapper;
import com.example.demo.dto.AllProjectDto;
import com.example.demo.dto.AllTagDto;
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
        logger.info("Add project " + project.toString());
        projectRepository.save(project);
    }

    public Project getProject(Long id) throws NoEntitiesException {
        logger.info("Find project by id: " + id);
        return projectRepository.findById(id).orElseThrow(() -> new NoEntitiesException("Project not found"));
    }

    public AllProjectDto getProjectDto(Long id) throws NoEntitiesException {
        logger.info("Convert Project into " + AllProjectDto.class.getName());
        return Mapper.getProjectDto(getProject(id));
    }

    public List<?> getProjectList() {
        logger.info("Get list Project ");
        return Mapper.getDtoList(projectRepository.findAll(), ProjectDto.class);
    }

    public void deleteProject(Long id) throws NoEntitiesException {
        logger.info("Delete Project " + id);
        projectRepository.delete(getProject(id));
    }

    public void addTagInProject(Long id_project, AllTagDto tagDto) throws NoEntitiesException, ValidationTagException {
        logger.info(String.format("Add Tag in Project id_project = %d tag ", id_project, tagDto.toString()));
        Project project = getProject(id_project);
        validationService.validate(tagDto.getName(), project);
        project.addTag(Mapper.modelMapper.map(tagDto, Tag.class));
        projectRepository.save(project);
    }

    public void setValidationInProject(Long id_project, String name) throws NoEntitiesException {
        logger.info(String.format("Set validation in Project id = %d validation = %s ", id_project, name));
        Project project = getProject(id_project);
        project.setValidation(name);
        projectRepository.save(project);
    }

    public void deleteValidation(Long id_project) throws NoEntitiesException {
        logger.info(String.format("Delete Validation Project id = %d"), id_project);
        Project project = getProject(id_project);
        project.resetValidationToDefault();
        projectRepository.save(project);
    }

}
