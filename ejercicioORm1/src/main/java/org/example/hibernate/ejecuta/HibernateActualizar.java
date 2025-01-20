package org.example.hibernate.ejecuta;

import jakarta.persistence.EntityManager;
import org.example.hibernate.entity.Clientes;
import org.example.hibernate.util.JpaUtil;

import javax.swing.*;

public class HibernateActualizar {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Long id = Long.valueOf(JOptionPane.showInputDialog("id"));

            Clientes c = em.find(Clientes.class, id);

            String nombre = JOptionPane.showInputDialog("nombre");
            String apellido = JOptionPane.showInputDialog("apellido");
            String formapago = JOptionPane.showInputDialog("formapago");

            em.getTransaction().begin();

            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setForma_pago(formapago);

            em.merge(c);
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
