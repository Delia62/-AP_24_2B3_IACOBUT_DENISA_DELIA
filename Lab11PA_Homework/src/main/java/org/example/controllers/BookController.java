package org.example.controllers;

import org.example.models.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1, "Book1"));
        books.add(new Book(2, "Book2"));
        books.add(new Book(3, "Book3"));
    }

    @GetMapping("/all")
    public List<Book> getBooks() {
        return books;
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @PutMapping("/{id}/title")
    public Book updateBookName(@PathVariable int id, @RequestParam String newName) {
        for (Book book : books) {
            if (book.getId() == id) {
                book.setName(newName);
                return book;
            }
        }
        throw new IllegalArgumentException("Book not found with ID: " + id);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId() == id) {
                iterator.remove();
                return "Book with ID " + id + " has been deleted successfully.";
            }
        }
        throw new IllegalArgumentException("Book not found with ID: " + id);
    }
}