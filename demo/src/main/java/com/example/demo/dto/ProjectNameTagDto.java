package com.example.demo.dto;

import java.util.Set;

public class ProjectNameTagDto {

    private Long id;

    private String name;

    private Set<TagNameDto> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
