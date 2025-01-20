package org.example.service;


import org.example.hibernate.entity.Clientes;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Clientes> listar();
    Optional<Clientes> buscar(Long id);
    void guardar(Clientes cliente);

    void eliminar(Long id);
}
