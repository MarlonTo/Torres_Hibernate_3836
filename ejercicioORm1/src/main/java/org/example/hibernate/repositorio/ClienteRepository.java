package org.example.hibernate.repositorio;


import jakarta.persistence.EntityManager;
import org.example.hibernate.entity.Clientes;

import java.util.List;

public class ClienteRepository implements CrudRepository<Clientes> {

        private EntityManager em;

        public ClienteRepository(EntityManager em) {
            this.em = em;
        }

        @Override
        public List<Clientes> listar() {
            return em.createQuery("select c from Clientes c", Clientes.class).getResultList();
        }

        @Override
        public Clientes porid(Long id) {

            return em.find(Clientes.class, id);
        }
        @Override
        public void guardar(Clientes clientes) {
            if (clientes.getId() != null && clientes.getId() > 0) {
                em.merge(clientes);
            } else {
                em.persist(clientes);
            }

        }
        @Override
        public void eliminar(Long Id) {
            Clientes clientes = porid(Id);
            em.remove(clientes);
        }

    @Override
    public void actualizar(Clientes producto) {
        em.merge(producto);

    }
}
