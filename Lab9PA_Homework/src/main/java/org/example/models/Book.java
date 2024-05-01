package org.example.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")

public class Book {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private Integer releaseYear;
    private String title;

    @OneToMany
    private List<Genre> genres;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne
    @JoinColumn(name = "publisher")
    private PublishingHouse houses;

    public PublishingHouse getHouses() {
        return houses;
    }

    public void setHouses(PublishingHouse houses) {
        this.houses = houses;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
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

    public void setGenres(List<Genre> genre) {
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
                ", houses=" + houses +
                '}';
    }
}
