package com.example.demo.controller;


import com.example.demo.dto.ProjectNameValidationTagDto;
import com.example.demo.entities.Project;
import com.example.demo.exception.NoEntitiesException;
import com.example.demo.exception.ValidationTagException;
import com.example.demo.service.ProjectService;
import com.example.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/Project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ReportService reportService;

    @RequestMapping(method = RequestMethod.POST)
    public void addProject(@RequestParam(value = "name") String name) {
        projectService.addProject(new Project(name));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<?> getAllProjects() {
        return projectService.getProjectList();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ProjectNameValidationTagDto getProject(@PathVariable(value = "id") Long id) throws NoEntitiesException {
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

    @RequestMapping(value = "{id}/Report", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> createReport(@PathVariable(value = "id") Long id) throws NoEntitiesException, IOException {
        ProjectNameValidationTagDto project = projectService.getProjectDto(id);
        byte[] response = reportService.getReportFile(project);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + project.getName() + ".txt\"");
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentLength(response.length);
        return new ResponseEntity<byte[]>(response, headers, HttpStatus.OK);
    }

}
