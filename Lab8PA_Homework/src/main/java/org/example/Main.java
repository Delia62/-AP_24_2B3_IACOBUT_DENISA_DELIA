package org.example;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String args[]) throws SQLException {
        try {
            var authors = new AuthorDAO();
          //  authors.create(68,"William Shakespeare");
            //authors.create(78,"Douglas Adams");
            //Database.getConnection().commit();

            //var genres = new GenreDAO();
            //genres.create("Tragedy");
           // Database.getConnection().commit();

            List<Author> allAuthors = authors.findAll();

            for (Author author : allAuthors) {
                System.out.println("ID: " + author.getId());
                System.out.println("Name: " + author.getName());
                System.out.println("Nationality: " + author.getNationality());
                System.out.println();
            }

            var books = new BookDAO();

            //books.create(803865,1597,"Romeo and Juliet","William Shakespeare","Tragedy");
            //books.create(95600,1979,"The Hitchhiker's Guide to the Galaxy",
                    //"Douglas Adams", "Science fiction, Comedy, Adventure");
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
            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback(Database.getConnection());
        }
    }
}
