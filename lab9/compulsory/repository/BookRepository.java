package org.example.repository;

import org.example.model.Book;
import org.example.DatabaseUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class BookRepository {
    private final EntityManager entityManager = DatabaseUtils.getInstance().getEntityManager();

    public BookRepository() {
    }

    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    public void create(Book book) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(book);
        this.entityManager.getTransaction().commit();
    }

    public Book findById(int id) {
        return (Book)this.entityManager.find(Book.class, id);
    }

    public List<Book> findByName(String name) {
        TypedQuery<Book> query = this.entityManager.createNamedQuery("Book.findByName", Book.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Book> findByAuthorId(Integer id) {
        TypedQuery<Book> query = this.entityManager.createNamedQuery("Book.findByAuthorId", Book.class);
        query.setParameter("authorId", id);
        return query.getResultList();
    }
}