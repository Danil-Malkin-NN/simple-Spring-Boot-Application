package com.example.demo.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "name can't be empty")
    private String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private final Set<Tag> tags = new HashSet<>();

    private String validation = ".*";

    public Project(@NotBlank String name) {
        this.name = name;
    }

    public Project() {
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public void resetValidationToDefault() {
        this.validation = ".*";
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.setProject(this);
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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", validation='" + validation + '\'' +
                '}';
    }
}
