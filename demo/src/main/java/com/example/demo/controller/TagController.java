package com.example.demo.controller;

import com.example.demo.exception.NoEntitiesException;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Tag")
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteTagInProject(@PathVariable(value = "id") Long id) throws NoEntitiesException {
        tagService.deleteTag(id);
    }

}
