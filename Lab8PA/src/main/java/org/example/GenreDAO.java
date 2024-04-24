package org.example;

import java.sql.*;

public class GenreDAO {
    private Connection connection;

    public GenreDAO() throws SQLException {
        this.connection = Database.getInstance().getConnection();
    }

    public void create(String genreName) throws SQLException {
        String query = "INSERT INTO genres (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, genreName);
            statement.executeUpdate();
        }
    }

    public Integer findByName(String genreName) throws SQLException {
        String query = "SELECT id FROM genres WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, genreName);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt(1) : null;
            }
        }
    }
}
