package org.example.hibernate.productos;

import jakarta.persistence.EntityManager;
import org.example.hibernate.entity.Productos;
import org.example.hibernate.util.JpaUtil;

import java.util.List;

public class CompletoHibernate {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("Conexión establecida");
        System.out.println("==============================Listado de productos==============================");
        List<Productos> productos =
                em.createQuery("select c from Productos c", Productos.class).getResultList();
        productos.forEach(System.out::println);

        System.out.println("==============================Buscar producto por id==============================");
        Productos p = em.find(Productos.class, 1L);
        System.out.println(p);

        System.out.println("==============================Guardar producto==============================");
        Productos productos1 = new Productos();
        productos1.setNombre("Fanta");
        productos1.setPrecio(1.5F);
        productos1.setPuntuacion(5);
        em.getTransaction().begin();
        em.persist(productos1);
        em.getTransaction().commit();
        System.out.println(productos1);
        System.out.println("==============================Listado de productos==============================");
        productos =
                em.createQuery("select c from Productos c", Productos.class).getResultList();
        productos.forEach(System.out::println);

        System.out.println("==============================Actualizar producto==============================");
        Productos p1 = em.find(Productos.class, 2L);
        em.getTransaction().begin();
        p1.setNombre("PIZZA");
        p1.setPrecio(2.5F);
        p1.setPuntuacion(4);
        em.merge(p1);
        em.getTransaction().commit();
        System.out.println(p1);
        System.out.println("==============================Listado de productos==============================");
        productos =
                em.createQuery("select c from Productos c", Productos.class).getResultList();
        productos.forEach(System.out::println);

        System.out.println("==============================Eliminar producto==============================");
        Productos p2 = em.find(Productos.class, 4L);
        em.getTransaction().begin();
        em.remove(p2);
        em.getTransaction().commit();
        System.out.println(p2);
    }

}
