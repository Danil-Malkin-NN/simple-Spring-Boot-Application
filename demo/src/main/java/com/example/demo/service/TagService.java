package com.example.demo.service;

import com.example.demo.entities.Tag;
import com.example.demo.exception.NoEntitiesException;
import com.example.demo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public Tag getTag(Long id) throws NoEntitiesException {
        return tagRepository.findById(id).orElseThrow(() -> new NoEntitiesException("Tag not found"));
    }

    public void deleteTag(Long id) throws NoEntitiesException {
        tagRepository.delete(getTag(id));
    }

}
