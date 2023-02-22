package main.repositories;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import main.tables.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class StudentRepository {
    private Connection connection;

    @SneakyThrows
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from student")
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                String address = rs.getString("address");
                int formId = rs.getInt("form_id");
                students.add(new Student(id, name, birthday, address, formId));
            }
            return students;
        }
    }


    @SneakyThrows
    public List<Student> findByFormId(int formId) {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from student where form_id = ?")) {
            ps.setInt(1, formId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    LocalDate birthday = rs.getDate("birthday").toLocalDate();
                    String address = rs.getString("address");
                    students.add(new Student(id, name, birthday, address, formId));
                }
                return students;
            }
        }
    }
}
