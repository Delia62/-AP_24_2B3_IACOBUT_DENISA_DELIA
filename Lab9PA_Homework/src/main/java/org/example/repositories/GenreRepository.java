package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.Database;
import org.example.models.Genre;

import java.util.List;

public class GenreRepository implements AbstractRepository<Genre> {
    public Genre findByID(int id) {
        try(EntityManager em = Database.getEntityManagerFactory().createEntityManager()){
            return em.find(Genre.class, id);
        }
    }

    public List<Genre> findByName(String name) {
        try(EntityManager em = Database.getEntityManagerFactory().createEntityManager()){
            return em.createQuery("SELECT g FROM Genre g WHERE g.name LIKE :name", Genre.class)
                    .setParameter("name", name)
                    .getResultList();
        }
    }
}
