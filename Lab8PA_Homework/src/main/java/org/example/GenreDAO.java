package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class GenreDAO {
    public int id = 0;



    public void create(String genreName) throws SQLException {
        String query = "INSERT INTO genres (name) VALUES (?)";
        Connection connection = Database.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, genreName);
            statement.executeUpdate();
        }
    }

    public Integer findByName(String genreName) throws SQLException {
        String query = "SELECT id FROM genres WHERE name = ?";
        Connection connection = Database.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, genreName);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt(1) : null;
            }
        }
    }
    public void importGenre() throws SQLException {
        Connection connection = Database.getConnection();
        String csvPath = "C:/Users/Delia/Documents/GitHub/-AP_24_2B3_IACOBUT_DENISA_DELIA/Lab8PA_Homework/src/main/java/org/example/goodreads_books.csv";
        try (CSVReader reader = new CSVReader(new FileReader(csvPath))) {
            String[] csvLine;
            csvLine = reader.readNext();
            for (int i = 1; i < 246; i++) {
                csvLine = reader.readNext();
                String genreName = csvLine[3];
                try (PreparedStatement statement = connection.prepareStatement(
                        "insert ignore into genre (id, name) values (?,?)")) {

                    statement.setInt(1, id+1);
                    statement.setString(2, genreName);
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
