package main.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FormFullDto {
    private int id;

    private String name;

    private String teacher;

    private List<StudentDto> students;
}
