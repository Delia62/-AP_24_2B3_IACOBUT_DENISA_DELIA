package org.example.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer releaseYear;
    private String title;

    @OneToMany
    private List<Genre> genres;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    //@ManyToOne
    //@JoinColumn(name = "publisher")
    //private PublishingHouse houses;

    public Book(int i, String book1) {
        this.id = i;
        this.title = book1;
    }

    public Book(Integer id, Integer releaseYear, String title, List<Genre> genres, Author author) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
        this.genres = genres;
        this.author = author;
      //  this.houses = houses;
    }

    public Book() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", releaseYear=" + releaseYear +
                ", title='" + title + '\'' +
                ", genres=" + genres +
                ", author=" + author +
                '}';
    }

    public void setName(String newName) {
        this.title = newName;
    }
}
