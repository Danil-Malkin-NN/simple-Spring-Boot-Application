package com.example.demo.dto;

import java.util.Set;

public class ProjectNameValidationTagDto {

    private Long id;

    private String name;

    private String validation;

    private Set<TagNameDto> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TagNameDto> getTags() {
        return tags;
    }

    public void setTags(Set<TagNameDto> tags) {
        this.tags = tags;
    }
}