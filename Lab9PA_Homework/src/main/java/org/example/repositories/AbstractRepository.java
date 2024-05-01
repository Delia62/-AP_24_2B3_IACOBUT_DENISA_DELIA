package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.Database;

import java.util.List;

public interface AbstractRepository<T> {
    default void create(T u) {
        try(EntityManager entityManager = Database.getEntityManagerFactory().createEntityManager()){
            entityManager.getTransaction().begin();
            entityManager.persist(u);
            entityManager.getTransaction().commit();
        }
    }
    T findByID(int id);
    List<T> findByName(String name);
}
