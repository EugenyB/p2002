package main;

import lombok.SneakyThrows;
import main.dto.StudentFullDto;
import main.repositories.FormRepository;
import main.repositories.StudentRepository;
import main.services.StudentFormService;
import main.tables.Form;
import main.ui.UI;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;

import static main.util.MyConstants.*;

public class Main {
    private Scanner scanner;

    private UI ui;

    private FormRepository formRepository;
    private StudentRepository studentRepository;

    private StudentFormService studentFormService;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        init();

        int m;
        while ((m = ui.menu())!=0) {
            switch (m) {
                case ADD_FORM -> {
                    Form form = ui.inputFormData();
                    formRepository.addForm(form.getName(), form.getTeacher());
                }
                case SHOW_ALL_FORMS -> {
                    ui.showAllForms(formRepository.findAll());
                }
                case FIND_FORM_BY_ID -> {
                    int id = ui.inputFormId();
                    ui.showForm(formRepository.getById(id));
                }
                case UPDATE_FORM -> {
                    int id = ui.inputFormId();
                    Optional<Form> form = formRepository.getById(id);
                    form.ifPresent(f->
                        {
                            f = ui.showFormForEdit(f);
                            formRepository.update(f);
                        });
                }
                case DELETE_FORM -> {
                    int id = ui.inputFormId();
                    Optional<Form> form = formRepository.getById(id);
                    form.ifPresent(f -> {
                        if (!formRepository.delete(f)) {
                            ui.showMessage(String.format("Can't delete form with id = %d", f.getId()));
                        }
                    });
                }

                case SHOW_ALL_STUDENTS -> {
                    ui.showAllStudents(studentRepository.findAll());
                }

                case SHOW_STUDENTS_BY_FORM -> {
                    int formId = ui.inputFormId();
                    List<StudentFullDto> studentsByForm = studentFormService.findStudentsByForm(formId);
                    ui.showListStudentsByForm(studentsByForm);
                }
            }
        }
    }

    @SneakyThrows
    private void init() {
        try (BufferedReader reader = Files.newBufferedReader(Path.of("p2002.properties"))) {
            Properties props = new Properties();
            props.load(reader);

            scanner = new Scanner(System.in);
            ui = new UI(scanner);
            Connection connection = DriverManager.getConnection(props.getProperty("url"), props);
            formRepository = new FormRepository(connection);
            studentRepository = new StudentRepository(connection);
            studentFormService = new StudentFormService(formRepository, studentRepository);
        }
    }

}

// CRUD
// C - Create
// R - read / retrieve
// U - update
// D - delete