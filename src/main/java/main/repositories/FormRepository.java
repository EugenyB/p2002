package main.repositories;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import main.tables.Form;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class FormRepository {
    private Connection connection;

    @SneakyThrows
    public List<Form> findAll() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from form")
        ) {
            List<Form> forms = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String teacher = resultSet.getString("teacher");
                forms.add(new Form(id, name, teacher));
            }
            return forms;
        }
    }

    @SneakyThrows
    public Optional<Form> getById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from form where id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String teacher = resultSet.getString("teacher");
                    return Optional.of(new Form(id, name, teacher));
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public boolean addForm(String name, String teacher) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into form (name, teacher) VALUES (?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, teacher);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean update(Form form) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "update form set name = ?, teacher = ? where id = ?")) {
            preparedStatement.setString(1, form.getName());
            preparedStatement.setString(2, form.getTeacher());
            preparedStatement.setInt(3, form.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from form where id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(Form form) {
        return delete(form.getId());
    }
}
