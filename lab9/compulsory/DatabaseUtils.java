package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseUtils {
    String PERSISTENCE_UNIT_NAME = "lab9_persistence_unit";
    private static DatabaseUtils instance;
    private final EntityManagerFactory entityManagerFactory;

    private DatabaseUtils() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(this.PERSISTENCE_UNIT_NAME);
    }

    public static DatabaseUtils getInstance() {
        if (instance == null) {
            instance = new DatabaseUtils();
        }

        return instance;
    }

    public EntityManager getEntityManager() {
        return this.entityManagerFactory.createEntityManager();
    }
}
