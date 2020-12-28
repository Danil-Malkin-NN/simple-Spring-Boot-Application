package com.example.demo.service;

import com.example.demo.entities.Project;
import com.example.demo.entities.Tag;
import org.springframework.stereotype.Service;

@Service(value = "CountAllTag")
public class CountAllTagProjectModule implements ProjectModule {

    @Override
    public String projectCheck(Project project) {
        int count = project.getTags().stream().mapToInt(Tag::getCount).sum();
        return String.valueOf(count);
    }

}
