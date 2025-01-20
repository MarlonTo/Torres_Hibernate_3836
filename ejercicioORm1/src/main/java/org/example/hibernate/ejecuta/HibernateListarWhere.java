package org.example.hibernate.ejecuta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.hibernate.entity.Clientes;
import org.example.hibernate.util.JpaUtil;

import java.util.List;
import java.util.Scanner;

public class HibernateListarWhere {
    public static void main(String[] args) {
        /*EntityManager em = JpaUtil.getEntityManager();
        Query query =
                em.createQuery("select c from Clientes c where c.forma_pago=?1", Clientes.class);
        query.setParameter(1, "Efectivo");
        Clientes c = (Clientes) query.getSingleResult();
        em.close();*/
        /*Scanner s = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Clientes c where c.forma_pago=?1", Clientes.class);
        System.out.println("Ingrese el pago:");
        String pago = s.next();
        query.setParameter(1, pago);
        List<Clientes> c =  query.getResultList();
        System.out.println(c);
        em.close();*/

       /* Scanner s = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();
        Query query =
                em.createQuery("select c from Clientes c where c.id=?1", Clientes.class);
        System.out.println("Ingrese el pago:");
        Long pago = s.nextLong();
        query.setParameter(1, pago);
        List<Clientes> c =  query.getResultList();
        System.out.println(c);
        em.close();*/

        Scanner s = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("Ingrese el id:");
        Long id = s.nextLong();
        Clientes c = em.find(Clientes.class, id);
        System.out.println(c);
        em.close();
    }
}
