package org.example;

import jakarta.persistence.EntityManagerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if(entityManagerFactory == null) {
            entityManagerFactory = jakarta.persistence.Persistence.createEntityManagerFactory("ExamplePU");
        }
        return entityManagerFactory;
    }

    private Database() {}
}
