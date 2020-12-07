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

    public void setCountForTag(Long id, Integer count) throws NoEntitiesException {
        Tag tag = getTag(id);
        tag.setCount(count);
        tagRepository.save(tag);
    }

    public void setPriceForTag(Long id, Integer price) throws NoEntitiesException {
        Tag tag = getTag(id);
        tag.setPrice(price);
        tagRepository.save(tag);
    }

    public Integer getCount(Long id) throws NoEntitiesException {
        return getTag(id).getCount();
    }

    public Integer getPrice(Long id) throws NoEntitiesException {
        return getTag(id).getPrice();
    }
}
