package org.example;

import org.example.model.Book;
import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;
import java.util.Iterator;
import java.util.List;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        new AuthorRepository();
        List<Book> books = bookRepository.findByAuthorId(3);
        Iterator var4 = books.iterator();

        while(var4.hasNext()) {
            Book book = (Book)var4.next();
            System.out.println(book);
        }

    }
}
