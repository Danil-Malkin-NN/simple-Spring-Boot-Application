package com.example.demo.service;

import com.example.demo.entities.Project;
import com.example.demo.entities.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "CountAllTag")
public class CountAllTagProjectModule implements ProjectModule {

    private static final Logger logger = LoggerFactory.getLogger(CountAllTagProjectModule.class);

    @Override
    public String projectCheck(Project project) {
        int count = project.getTags().stream().mapToInt(Tag::getCount).sum();
        logger.info("Counting the total number of tags" + project.toString() + "count =" + count);
        return String.valueOf(count);
    }

}
