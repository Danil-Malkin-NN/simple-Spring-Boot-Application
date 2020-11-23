package com.example.demo.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "name can't be empty")
    @Column(unique = true)
    private String name;

    public Project(@NotBlank String name) {
        this.name = name;
    }

    public Project() {
    }

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
}
