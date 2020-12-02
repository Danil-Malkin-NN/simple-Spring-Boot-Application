package com.example.demo.service;

import com.example.demo.entities.Project;
import com.example.demo.exception.ValidationTagException;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidationService {

    public void validate(String name, Project project) throws ValidationTagException {
        if (!Pattern.matches(project.getValidation(), name)) {
            throw new ValidationTagException("Invalid tag name");
        }
    }
}
