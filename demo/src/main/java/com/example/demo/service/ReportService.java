package com.example.demo.service;

import com.example.demo.dto.ProjectNameValidationTagDto;
import com.example.demo.dto.TagNameDto;
import com.example.demo.exception.NoEntitiesException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

@Service
public class ReportService {

    public byte[] getReportFile(ProjectNameValidationTagDto project) throws NoEntitiesException, IOException {

        String format = ".txt";
        String name = "Name project: ";
        String valid = "the project has limitations: ";
        String tagProject = "Project has tag: ";
        try {
            PrintWriter writer = new PrintWriter(project.getName() + format);
            writer.println(name + project.getName());
            writer.println(valid + project.getValidation());
            writer.print(tagProject);
            for (TagNameDto tag : project.getTags()) {
                writer.println(tag.getName());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return Files.readAllBytes(new File(project.getName() + format).toPath());
    }
}
