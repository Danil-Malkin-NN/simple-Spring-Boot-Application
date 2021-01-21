package com.example.demo.service;

import com.example.demo.dto.AllProjectDto;

import java.io.IOException;

public interface ProjectReport {

    byte[] generateReport(AllProjectDto projectDto) throws IOException;

}
