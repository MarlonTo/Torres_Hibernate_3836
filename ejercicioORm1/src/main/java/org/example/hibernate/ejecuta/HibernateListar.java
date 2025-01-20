package org.example.hibernate.ejecuta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.hibernate.entity.Clientes;
import org.example.hibernate.util.JpaUtil;

import java.util.List;

public class HibernateListar {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        List<Clientes> clientes =
                em.createQuery("select c from Clientes c", Clientes.class).getResultList();
        clientes.forEach(System.out::println);
        em.close();
    }
}
