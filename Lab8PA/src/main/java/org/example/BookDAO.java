package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO() throws SQLException {
        this.connection = Database.getInstance().getConnection();
    }

    public void create(int id, int an, String title, String author, String genre) throws SQLException {
        String query = "INSERT INTO books (id, an, title, author, genre) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setInt(2, an);
            statement.setString(3, title);
            statement.setString(4, author);
            statement.setString(5, genre);
            statement.executeUpdate();
        }
    }

    public Book findById(int id_book) throws SQLException {
        String query = "SELECT * FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_book);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Book(resultSet.getInt("id"),
                            resultSet.getInt("an"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("genre"));
                }
            }
        }
        return null;
    }

    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt("id"),
                        resultSet.getInt("an"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("genre"));
                books.add(book);
            }
        }
        return books;
    }
}
