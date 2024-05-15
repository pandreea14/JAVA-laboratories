package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "books",
        schema = "books"
)
@NamedQueries({@NamedQuery(
        name = "Book.findByName",
        query = "Select b from Book b where b.title like :name"
), @NamedQuery(
        name = "Book.findByAuthorId",
        query = "select b from Book b Join BookAuthor ba ON b.id = ba.book.id where ba.author.id = :authorId "
)})
public class Book {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id",
            nullable = false
    )
    private Integer id;
    @Lob
    @Column(
            name = "title",
            nullable = false
    )
    private String title;
    @Lob
    @Column(
            name = "genre"
    )
    private String genre;
    @Column(
            name = "publication_date"
    )
    private String publicationDate;

    public Book() {
    }

    public String toString() {
        return "Book{id=" + this.id + ", title='" + this.title + "', genre='" + this.genre + "', publicationDate='" + this.publicationDate + "'}";
    }

    public Integer getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getPublicationDate() {
        return this.publicationDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
}