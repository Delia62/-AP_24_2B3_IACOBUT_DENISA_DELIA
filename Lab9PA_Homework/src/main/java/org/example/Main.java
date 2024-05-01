package org.example;


import org.example.models.Author;
import org.example.models.Book;
import org.example.models.Genre;
import org.example.models.PublishingHouse;
import org.example.repositories.AuthorRepository;
import org.example.repositories.BookRepository;
import org.example.repositories.GenreRepository;
import org.example.repositories.PublishingHouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
    try {

        FileHandler fileHandler = new FileHandler("logfile.log");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        long currentTime = System.currentTimeMillis();

        PublishingHouse publishingHouse1 = new PublishingHouse();
        publishingHouse1.setName("CreativeWorks Advertising");
        new PublishingHouseRepository().create(publishingHouse1);

        PublishingHouse publishingHouse2 = new PublishingHouse();
        publishingHouse2.setName("BrandBoost Media Agency");
        new PublishingHouseRepository().create(publishingHouse2);

        PublishingHouse publishingHouse3 = new PublishingHouse();
        publishingHouse3.setName("PixelPerfect Marketing");
        new PublishingHouseRepository().create(publishingHouse3);


        Author author1 = new Author();
        author1.setName("F. Scott Fitzgerald");
        new AuthorRepository().create(author1);

        Author author2 = new Author();
        author2.setName("Jane Austen");
        new AuthorRepository().create(author2);

        Author author3 = new Author();
        author3.setName("J.D. Salinger");
        new AuthorRepository().create(author3);


        Genre genre1 = new Genre();
        genre1.setName("Fantasy");
        new GenreRepository().create(genre1);
        Genre genre2 = new Genre();

        genre2.setName("Thriller psihologic");
        new GenreRepository().create(genre2);
        Genre genre3 = new Genre();

        genre3.setName("Science fiction");
        new GenreRepository().create(genre3);



        Book book = new Book();
        book.setTitle("The Great Gatsby");
        book.setReleaseYear(1925);
        book.setAuthor(author1);
        List<Genre> genres1 = new ArrayList<>();
        genres1.add(genre1);
        book.setGenres(genres1);
        book.setHouses(publishingHouse1);
        new BookRepository().create(book);

        Book book2 = new Book();
        book2.setTitle("Pride and Prejudice");
        book2.setReleaseYear(1813);
        book2.setAuthor(author2);
        List<Genre> genres2 = new ArrayList<>();
        genres2.add(genre2);
        book2.setGenres(genres2);
        book2.setHouses(publishingHouse2);
        new BookRepository().create(book2);

        Book book3 = new Book();
        book3.setTitle("The Catcher in the Rye");
        book3.setReleaseYear(1813);
        book3.setAuthor(author3);
        List<Genre> genres3 = new ArrayList<>();
        genres3.add(genre3);
        book3.setGenres(genres3);
        book3.setHouses(publishingHouse3);
        new BookRepository().create(book3);


        long time = System.currentTimeMillis() - currentTime;
        System.out.println("Took " + time + " milliseconds");
        fileHandler.publish(new LogRecord(Level.INFO, "Execution time of JPQL statement: " + time + " ms"));

        System.out.println("An author of your choice after id is " + new BookRepository().findByID(1).getAuthor().getName());

        List<Author> authors = new AuthorRepository().findByName("Jane Austen");
        for (Author author : authors) {
            System.out.println("Author name: " + author.getName());
        }

        List<Genre> genres = new GenreRepository().findByName("Fantasy");
        for (Genre genre : genres) {
            System.out.println("Genres: " + genre.getName());
        }

        List<PublishingHouse> publishingHouses = new PublishingHouseRepository().findByName("CreativeWorks Advertising");
        for (PublishingHouse publishingHouse : publishingHouses) {
            System.out.println("Publishing house: " + publishingHouse.getName());
        }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An exception occurred", e);
        }
    }
}