package org.example;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        try {
            var authors = new AuthorDAO();
            authors.create(90,"William Shakespeare");
            authors.create(91,"Douglas Adams");
            var genres = new GenreDAO();
            genres.create("Tragedy");
            Database.createConnection();

            List<Author> allAuthors = authors.findAll();
            for (Author author : allAuthors) {
                System.out.println("ID: " + author.getId());
                System.out.println("Name: " + author.getName());
                System.out.println("Nationality: " + author.getNationality());
                System.out.println();
            }

            //Database.getConnection().commit();
            var books = new BookDAO(); //findByName
            books.create(20001,1597,"Romeo and Juliet","William Shakespeare","Tragedy");
            books.create(20009,1979,"The Hitchhiker's Guide to the Galaxy",
                    "Douglas Adams", "Science fiction, Comedy, Adventure");
            //Database.getConnection().commit();

            List<Book> allBooks = books.findAll();
            for (Book book : allBooks) {
                System.out.println("ID: " + book.getId());
                System.out.println("An: " + book.getAn());
                System.out.println("Titlu: " + book.getTitle());
                System.out.println("Autor: " + book.getAuthor());
                System.out.println("Gen: " + book.getGenre());
                System.out.println();
            }
            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e);
           //Database.rollback();
        }
    }
}
