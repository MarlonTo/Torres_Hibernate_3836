package org.example.hibernate.ejecuta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.hibernate.entity.Clientes;
import org.example.hibernate.util.JpaUtil;

import javax.swing.*;

public class HibernateCrear {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String nombre = JOptionPane.showInputDialog("nombre");
            String apellido = JOptionPane.showInputDialog("apellido");
            String formapago = JOptionPane.showInputDialog("formapago");
            em.getTransaction().begin();
            Clientes c = new Clientes();
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setForma_pago(formapago);
            em.persist(c);
            em.getTransaction().commit();
            System.out.println(c);
        }catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }

    }

}
