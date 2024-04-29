package org.example;

import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private LocalDate publicationDate;
    private int numberOfPages;
    private String language;

    public Book(int id, String title, LocalDate publicationDate, int numberOfPages, String language) {
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.numberOfPages = numberOfPages;
        this.language = language;
    }


    public String getTitle() {
        return this.title;
    }

    public LocalDate getPublicationDate() {
        return this.publicationDate;
    }

    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    public String getLanguage() {
        return this.language;
    }
}
