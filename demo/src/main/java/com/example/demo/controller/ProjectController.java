package com.example.demo.controller;

import com.example.demo.dto.AllProjectDto;
import com.example.demo.entities.Project;
import com.example.demo.exception.FormatNotFoundException;
import com.example.demo.exception.ModuleNotSupportedException;
import com.example.demo.exception.NoEntitiesException;
import com.example.demo.exception.ValidationTagException;
import com.example.demo.service.*;
import com.example.demo.utilities.HeadersUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    SelectorReportService selectorReportService;

    @Autowired
    SelectorProjectModule selectorProjectModule;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void addProject(@RequestParam(value = "name") String name) {
        projectService.addProject(new Project(name));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<?> getAllProjects() {
        return projectService.getProjectList();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public AllProjectDto getProject(@PathVariable(value = "id") Long id) throws NoEntitiesException {
        return projectService.getProjectDto(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable(value = "id") Long id) throws NoEntitiesException {
        projectService.deleteProject(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
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
    public ResponseEntity<?> createReport(@PathVariable(value = "id") Long id, @RequestParam String format) throws
                                                                                                            NoEntitiesException,
                                                                                                            IOException,
                                                                                                            FormatNotFoundException {
        AllProjectDto project = projectService.getProjectDto(id);
        ProjectReport projectReport = selectorReportService.getProjectReport(format);
        byte[] response = projectReport.generateReport(project);
        HttpHeaders httpHeaders = HeadersUtilities.getHttpHeaders(project.getName(), format);
        return new ResponseEntity<byte[]>(response, httpHeaders, HttpStatus.OK);

    }

    @RequestMapping(value = "{id}/Module/{moduleName}", method = RequestMethod.GET)
    public String moduleAction(@PathVariable(value = "id") Long id,
                                       @PathVariable(value = "moduleName") String moduleName) throws NoEntitiesException, ModuleNotSupportedException {
        Project project = projectService.getProject(id);
        ProjectModule projectModule = selectorProjectModule.getModule(moduleName);
        return projectModule.projectCheck(project);
    }

}
