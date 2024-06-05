package org.example.lab11.demo.service;

import org.example.lab11.demo.model.Book;
import org.example.lab11.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthors(bookDetails.getAuthors());
            book.setGenre(bookDetails.getGenre());
            book.setPublicationDate(bookDetails.getPublicationDate());
            return bookRepository.save(book);
        }).orElseGet(() -> {
            bookDetails.setId(id);
            return bookRepository.save(bookDetails);
        });
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
