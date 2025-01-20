package org.example.hibernate.repositorio;

import jakarta.persistence.EntityManager;
import org.example.hibernate.entity.Productos;

import java.util.List;

public class ProductoRepository implements CrudRepository<Productos> {

    private EntityManager em;

    public ProductoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Productos> listar() {
        return em.createQuery("select p from Productos p", Productos.class).getResultList();
    }

    @Override
    public Productos porid(Long id) {

        return em.find(Productos.class, id);
    }
    @Override
    public void guardar(Productos productos) {
        if (productos.getId() != null && productos.getId() > 0) {
            em.merge(productos);
        } else {
            em.persist(productos);
        }

    }
    @Override
    public void eliminar(Long Id) {
        Productos productos = porid(Id);
        em.remove(productos);
    }

    @Override
    public void actualizar(Productos productos) {
        if (productos.getId() != null && productos.getId() > 0) {
            em.merge(productos);
        } else {
            em.persist(productos);
        }
    }
}
