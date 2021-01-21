package com.example.demo.service;

import com.example.demo.dto.AllProjectDto;
import com.example.demo.dto.TagDto;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

@Service(value = "ProjectReport_txt")
public class ReportTxtService implements ProjectReport {

    @Override
    public byte[] generateReport(AllProjectDto projectDto) throws IOException {
        String format = ".txt";
        String name = "Name project: ";
        String valid = "the project has limitations: ";
        String tagProject = "Project has tag: ";

        File tmpFile = File.createTempFile(projectDto.getName() + format, null);
        PrintWriter writer = new PrintWriter(tmpFile);
        writer.println(name + projectDto.getName());
        writer.println(valid + projectDto.getValidation());
        writer.print(tagProject);
        for (TagDto tag : projectDto.getTags()) {
            writer.println(tag.getName());
        }
        writer.close();
        byte[] result = Files.readAllBytes(tmpFile.toPath());
        tmpFile.delete();
        return result;
    }
}
