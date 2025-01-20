package org.example.service;

import jakarta.persistence.EntityManager;
import org.example.hibernate.entity.Productos;
import org.example.hibernate.repositorio.CrudRepository;
import org.example.hibernate.repositorio.ProductoRepository;

import java.util.List;
import java.util.Optional;

public class ProductoServiceImplementa implements ProductoService {
    private EntityManager em;
    private CrudRepository<Productos> repositorio;

    public ProductoServiceImplementa(EntityManager em){
        this.em = em;
        this.repositorio = new ProductoRepository(em);
    }

    @Override
    public List<Productos> listar() {
        return repositorio.listar();
    }

    @Override
    public Optional<Productos> buscar(Long id) {
        return Optional.ofNullable(repositorio.porid(id));
    }

    @Override
    public void guardar(Productos producto) {
        try{
            em.getTransaction().begin();
            repositorio.guardar(producto);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }

    }

    @Override
    public void eliminar(Long id){
        try{
            em.getTransaction().begin();
            repositorio.eliminar(id);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

    @Override
    public void actualizar(Productos producto) {
        try{
            em.getTransaction().begin();
            repositorio.actualizar(producto);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }
}
