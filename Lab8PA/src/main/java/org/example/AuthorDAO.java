package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private Connection connection;

    public AuthorDAO() throws SQLException {
        this.connection = Database.getInstance().getConnection();
    }

    public Author getAuthorById(int id_author) throws SQLException {
        String query = "SELECT * FROM authors WHERE id = ?";
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

    public void create(int id_author, String name_author) throws SQLException {
        String query = "INSERT INTO authors (id, name) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id_author);
            pstmt.setString(2, name_author);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        String query = "SELECT id FROM authors WHERE name=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next() ? rs.getInt(1) : null;
            }
        }
    }
    public List<Author> findAll() throws SQLException {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM authors";
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


    public String findById(int id_author) throws SQLException {
        String query = "SELECT name FROM authors WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_author);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
            }
        }
        return null;
    }
}
