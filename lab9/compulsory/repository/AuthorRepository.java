package org.example.repository;


import org.example.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.example.DatabaseUtils;

public class AuthorRepository {
    private final EntityManager entityManager = DatabaseUtils.getInstance().getEntityManager();

    public AuthorRepository() {
    }

    public void create(Author author) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(author);
        this.entityManager.getTransaction().commit();
    }

    public Author findById(int id) {
        return (Author)this.entityManager.find(Author.class, id);
    }

    public List<Author> findByName(String name) {
        TypedQuery<Author> query = this.entityManager.createNamedQuery("Author.findByName", Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
