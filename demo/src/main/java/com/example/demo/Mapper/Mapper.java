package com.example.demo.Mapper;

import com.example.demo.dto.AllProjectDto;
import com.example.demo.entities.Project;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static ModelMapper modelMapper = new ModelMapper();

    public static List<?> getDtoList(List<?> list, Type dtoClass) {
        List<?> dto = new ArrayList<>();
        list.forEach(person -> {
            dto.add(modelMapper.map(person, dtoClass));
        });
        return dto;
    }

    public static AllProjectDto getProjectDto(Project project) {
        return modelMapper.map(project, AllProjectDto.class);
    }

}
