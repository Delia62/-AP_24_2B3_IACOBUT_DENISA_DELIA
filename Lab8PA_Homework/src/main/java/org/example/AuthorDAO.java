package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {

    public int id = 0;


    public void create(int id, String name) throws SQLException {
        String query = "INSERT INTO authors (id, name) VALUES (?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        }
    }


    public Author findById(int id_author) throws SQLException {
        String query = "SELECT * FROM authors WHERE id = ?";
        Connection connection = Database.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_author);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String nationality = resultSet.getString("nationality");
                    return new Author(id_author, name, nationality);
                }
            }
        }
        return null;
    }

    public Integer findByName(String name) throws SQLException {
        String query = "SELECT id FROM authors WHERE name=?";
        Connection connection = Database.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next() ? rs.getInt(1) : null;
            }
        }
    }
    public void importAuthor() throws SQLException {
        Connection connection = Database.getConnection();
        String csvPath = "C:/Users/Delia/Documents/GitHub/-AP_24_2B3_IACOBUT_DENISA_DELIA/Lab8PA_Homework/src/main/java/org/example/goodreads_books.csv";
        try (CSVReader reader = new CSVReader(new FileReader(csvPath))) {
            String[] csvLine;
            csvLine = reader.readNext();
            for (int i = 1; i < 246; i++) {
                csvLine = reader.readNext();
                String authorName = csvLine[2];
                try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT IGNORE INTO author (id, name) values (?,?)")) {
                    statement.setInt(1, id+1);
                    statement.setString(2, authorName);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }

    }
    public List<Author> findAll() throws SQLException {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM authors";
        Connection connection = Database.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Author author = new Author(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("nationality"));
                authors.add(author);
            }
        }
        return authors;
    }

}
