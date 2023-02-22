package main.services;

import lombok.AllArgsConstructor;
import main.dto.StudentFullDto;
import main.repositories.FormRepository;
import main.repositories.StudentRepository;
import main.tables.Form;
import main.tables.Student;
import main.util.Convertor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class StudentFormService {
    private FormRepository formRepository;
    private StudentRepository studentRepository;

    public List<StudentFullDto> findStudentsByForm(int formId) {
        Optional<Form> form = formRepository.getById(formId);
        if (form.isEmpty()) return Collections.emptyList();
        List<Student> students = studentRepository.findByFormId(formId);
        return students.stream().map(s -> Convertor.studentToStudentFullDto(s, form.get())).toList();
    }
}
