package org.example.service;

import jakarta.persistence.EntityManager;
import org.example.hibernate.entity.Clientes;
import org.example.hibernate.repositorio.ClienteRepository;
import org.example.hibernate.repositorio.CrudRepository;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImplementa implements ClienteService{
    private EntityManager em;
    private CrudRepository<Clientes> repositorio;

    public ClienteServiceImplementa(EntityManager em){
        this.em = em;
        this.repositorio = new ClienteRepository(em);
    }

    @Override
    public List<Clientes> listar() {
        return repositorio.listar();
    }

    @Override
    public Optional<Clientes> buscar(Long id) {
        return Optional.ofNullable(repositorio.porid(id));
    }

    @Override
    public void guardar(Clientes clientes) {
        try{
            em.getTransaction().begin();
            repositorio.guardar(clientes);
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
}