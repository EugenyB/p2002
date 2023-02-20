package main.ui;

import lombok.AllArgsConstructor;
import main.tables.Form;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
public class UI {
    private Scanner scanner;

    public Form inputFormData() {
        System.out.print("name: ");
        String name = scanner.nextLine();
        System.out.print("teacher: ");
        String teacher = scanner.nextLine();
        return new Form(0, name, teacher);
    }

    public void showAllForms(List<Form> forms) {
        forms.forEach(System.out::println);
    }

    public int inputId() {
        System.out.print("Input form id: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void showForm(Optional<Form> form) {
        if (form.isEmpty()) {
            System.out.println("Nothing found");
        } else {
            System.out.println(form.get());
        }
    }

    public Form showFormForEdit(Form f) {
        System.out.println("name: " + f.getName());
        System.out.print("new name: ");
        String newName = scanner.nextLine();
        System.out.println("teacher: " + f.getTeacher());
        System.out.print("new teacher: ");
        String newTeacher = scanner.nextLine();
        if (!newName.isBlank()) f.setName(newName);
        if (!newTeacher.isBlank()) f.setTeacher(newTeacher);
        return f;
    }
}
