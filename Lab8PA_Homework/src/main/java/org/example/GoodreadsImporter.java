package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GoodreadsImporter {

    public int id = 0;


    public void importData() throws FileNotFoundException {
        Connection connection = Database.getConnection();
        var author = new AuthorDAO();
        String csvPath = "C:/Users/Delia/Documents/GitHub/-AP_24_2B3_IACOBUT_DENISA_DELIA/Lab8PA_Homework/src/main/java/org/example/goodreads_books.csv";
        try (CSVReader reader = new CSVReader(new FileReader(csvPath))) {
            String[] csvLine;
            csvLine = reader.readNext();
            for (int i = 1; i < 246; i++) {
                csvLine = reader.readNext();
                String bookId = csvLine[0];
                String title = csvLine[1];
                String authors = csvLine[2];
                String genre = csvLine[3];
                String year = csvLine[10].split("/")[3];
                try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT ignore INTO books (id, an, title, author, genre) values (?,?,?,?,?)")){
                    statement.setString(1, bookId);
                    statement.setString(2, year);
                    statement.setString(3, title);
                    statement.setString(4, authors);
                    statement.setString(4, genre);


                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection.commit();
                try (PreparedStatement statement = connection.prepareStatement(
                        "insert into author (id, name ) values (?,?)")) {

                    statement.setInt(1, id+1);
                    statement.setString(2, authors);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
