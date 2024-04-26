package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public void create(int id, int year, String title, String author, String genre) throws SQLException {
        String query = "INSERT INTO books (id, an, title, author, genre) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setInt(2, year);
            statement.setString(3, title);
            statement.setString(4, author);
            statement.setString(5, genre);
            statement.executeUpdate();
        }
    }

    public Book findById(int id_book) throws SQLException {
        String query = "SELECT * FROM books WHERE id = ?";
        Connection connection = Database.getConnection();
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
        Connection connection = Database.getConnection();
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
    public void importBook() throws SQLException {
        Connection connection = Database.getConnection();
        String csvPath = "C:/Users/Delia/Documents/GitHub/-AP_24_2B3_IACOBUT_DENISA_DELIA/Lab8PA_Homework/src/main/java/org/example/goodreads_books.csv";
        try (CSVReader reader = new CSVReader(new FileReader(csvPath))) {
            String[] csvLine;
            csvLine = reader.readNext();
            for (int i = 1; i < 246; i++) {
                csvLine = reader.readNext();
                String id = csvLine[0];
                String title = csvLine[1];
                String author = csvLine[2];
                String year = csvLine[10].split("/")[3];
                String genre = csvLine[11];
                try (PreparedStatement statement = connection.prepareStatement(
                        "insert ignore into books (id,an,title,author,genre) values (?,?,?,?,?);")) {

                    statement.setString(1, id);
                    statement.setString(2, year);
                    statement.setString(3, title);
                    statement.setString(4, author);
                    statement.setString(5, genre);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
