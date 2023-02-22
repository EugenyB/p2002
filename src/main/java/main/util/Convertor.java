package main.util;

import main.dto.FormDto;
import main.dto.StudentDto;
import main.dto.StudentFullDto;
import main.tables.Form;
import main.tables.Student;

public class Convertor {
    public static StudentDto studentToStudentDto(Student student) {
        return new StudentDto(student.getId(), student.getName(), student.getBirthday(), student.getAddress());
    }

    public static StudentFullDto studentToStudentFullDto(Student student, Form form) {
        return new StudentFullDto(student.getId(), student.getName(), student.getBirthday(), student.getAddress(), formToFormDto(form));
    }

    private static FormDto formToFormDto(Form form) {
        return new FormDto(form.getId(), form.getName(), form.getTeacher());
    }
}
