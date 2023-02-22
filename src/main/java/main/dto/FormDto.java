package main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FormDto {
    private int id;

    private String name;

    private String teacher;

    @Override
    public String toString() {
        return name;
    }
}
