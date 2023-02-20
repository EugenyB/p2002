package main;

import lombok.SneakyThrows;
import main.repositories.FormRepository;
import main.tables.Form;
import main.ui.UI;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;

import static main.util.MyConstants.*;

public class Main {
    private Scanner scanner;

    private UI ui;

    private FormRepository formRepository;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        init();

        int m;
        while ((m = menu())!=0) {
            switch (m) {
                case ADD_FORM -> {
                    Form form = ui.inputFormData();
                    formRepository.addForm(form.getName(), form.getTeacher());
                }
                case SHOW_ALL -> {
                    ui.showAllForms(formRepository.findAll());
                }
                case FIND_FORM_BY_ID -> {
                    int id = ui.inputId();
                    ui.showForm(formRepository.getById(id));
                }
                case UPDATE_FORM -> {
                    int id = ui.inputId();
                    Optional<Form> form = formRepository.getById(id);
                    form.ifPresent(f->
                        {
                            f = ui.showFormForEdit(f);
                            formRepository.update(f);
                        });
                }
                case DELETE_FORM -> {
                    int id = ui.inputId();
                    Optional<Form> form = formRepository.getById(id);
                    form.ifPresent(f -> formRepository.delete(f));
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
        }
    }

    private int menu() {
        System.out.println("""
                1. Add Form
                2. Show All Forms
                3. Find Form By Id
                4. Update Form
                5. Delete Form
                0. Exit
                """);
        int res = scanner.nextInt();
        scanner.nextLine();
        return res;
    }
}

// CRUD
// C - Create
// R - read / retrieve
// U - update
// D - delete