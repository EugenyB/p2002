package main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class StudentFullDto {
    private int id;
    private String name;
    private LocalDate birthday;
    private String address;
    private FormDto form;
}
