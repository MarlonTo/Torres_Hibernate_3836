package org.example.hibernate.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory entityManagerFactory=buidEntityManagerFactory();
    private static EntityManagerFactory buidEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("ejemploJPA");
    }
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
