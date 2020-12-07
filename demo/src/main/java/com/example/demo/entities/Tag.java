package com.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tag", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "project"}))
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @Min(value = 0, message = "The count cannot be less than 0")
    private Integer count = 0;

    @Min(value = 0, message = "The price cannot be less than 0")
    private Integer price = 0;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project")
    private Project project;

    public Tag() {
    }

    public Tag(@NotBlank String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
