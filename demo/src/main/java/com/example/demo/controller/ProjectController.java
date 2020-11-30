package com.example.demo.controller;


import com.example.demo.dto.ProjectNameTagDto;
import com.example.demo.entities.Project;
import com.example.demo.exception.NoEntitiesException;
import com.example.demo.exception.ValidationTagException;
import com.example.demo.service.ProjectService;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Project")
public class ProjectController {

    @Autowired
    ProjectService projectService;
    @Autowired
    TagService tagService;

    @RequestMapping(method = RequestMethod.POST)
    public void addProject(@RequestParam(value = "name") String name) {
        projectService.addProject(new Project(name));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<?> getAllProjects() {
        return projectService.getProjectList();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ProjectNameTagDto getProject(@PathVariable(value = "id") Long id) throws NoEntitiesException {
        return projectService.getProjectDto(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable(value = "id") Long id) throws NoEntitiesException {
        projectService.deleteProject(id);
    }

    @RequestMapping(value = "{id}/Tag/{name}", method = RequestMethod.POST)
    public void addTagInProject(@PathVariable(value = "id") Long id_project,
                                @PathVariable(value = "name") String name) throws NoEntitiesException, ValidationTagException {
        projectService.addTagInProject(id_project, name);
    }

    @RequestMapping(value = "{id}/TagValidation", method = RequestMethod.POST)
    public void addValidationInProject(@PathVariable(value = "id") Long id_project, @RequestBody String name) throws NoEntitiesException {
        projectService.setValidationInProject(id_project, name);
    }

    @RequestMapping(value = "{id}/TagValidation", method = RequestMethod.DELETE)
    public void deleteValidationInProject(@PathVariable(value = "id") Long id_project) throws NoEntitiesException {
        projectService.deleteValidation(id_project);
    }

}
