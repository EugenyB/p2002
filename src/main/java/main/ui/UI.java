package main.ui;

import lombok.AllArgsConstructor;
import main.dto.StudentFullDto;
import main.tables.Form;
import main.tables.Student;

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
        System.out.println("=== All Forms ===");
        showList(forms);
    }


    public void showAllStudents(List<Student> students) {
        System.out.println("=== All Students ===");
        showList(students);
    }

    public void showListStudentsByForm(List<StudentFullDto> studentsByForm) {
        if (!studentsByForm.isEmpty()) {
            System.out.println("=== Students from " + studentsByForm.get(0).getForm().getName() + " ===");
            showList(studentsByForm);
        }
    }

    public void showList(List<?> items) {
        items.forEach(System.out::println);
        System.out.println("-----------------");
    }

    public int inputFormId() {
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

    public int menu() {
        System.out.println("""
                1. Add Form
                2. Show All Forms
                3. Find Form By Id
                4. Update Form
                5. Delete Form
                ---------------
                6. Show All Students
                7. Show Students By Form
                0. Exit
                """);
        int res = scanner.nextInt();
        scanner.nextLine();
        return res;
    }

    public void showMessage(String message) {
        System.out.println(">>> " + message + " <<<");
    }

}
