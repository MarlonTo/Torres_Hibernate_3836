package org.example.service;

import org.example.hibernate.entity.Productos;

import java.util.List;
import java.util.Optional;

public interface ProductoService
{
    List<Productos> listar();
    Optional<Productos> buscar(Long id);
    void guardar(Productos producto);
    void eliminar(Long id);
    void actualizar(Productos producto);
}
