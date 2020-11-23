package com.example.demo.controller;


import com.example.demo.entities.Project;
import com.example.demo.exception.NoEntitiesException;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(method = RequestMethod.POST)
    public void addProject(@RequestParam(value = "name") String name) {
        projectService.addProject(new Project(name));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Project> getAllProjects() {
        return projectService.getProjectList();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Project getProject(@PathVariable(value = "id") Long id) {
        return projectService.getProject(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable(value = "id") Long id) throws NoEntitiesException {
        projectService.deleteProject(id);
    }

}
