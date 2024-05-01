package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.Database;
import org.example.models.PublishingHouse;

import java.util.List;

public class PublishingHouseRepository implements AbstractRepository<PublishingHouse> {
    public PublishingHouse findByID(int id) {
        try(EntityManager em = Database.getEntityManagerFactory().createEntityManager()){
            return em.find(PublishingHouse.class, id);
        }
    }

    public List<PublishingHouse> findByName(String name) {
        try(EntityManager em = Database.getEntityManagerFactory().createEntityManager()){
            return em.createQuery("SELECT g FROM PublishingHouse g WHERE g.name LIKE :name", PublishingHouse.class)
                    .setParameter("name", name)
                    .getResultList();
        }
    }
    }