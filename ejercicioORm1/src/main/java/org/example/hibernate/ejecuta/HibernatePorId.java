package org.example.hibernate.ejecuta;

import jakarta.persistence.EntityManager;
import org.example.hibernate.entity.Clientes;
import org.example.hibernate.util.JpaUtil;

import java.util.Scanner;

public class HibernatePorId {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("Ingrese el id:");
        Long id = s.nextLong();
        Clientes c = em.find(Clientes.class, id);
        System.out.println(c);
        em.close();
    }
}

