package com.example.demo.service;

import com.example.demo.dto.ProjectNameValidationTagDto;

import java.io.IOException;

public interface ProjectReport {
    
    byte[] generateReport(ProjectNameValidationTagDto projectDto) throws IOException;

}
