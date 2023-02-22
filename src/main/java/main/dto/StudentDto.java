package main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.tables.Form;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class StudentDto {
    private int id;
    private String name;
    private LocalDate birthday;
    private String address;

}
