package org.example.hibernate.repositorio;

import org.example.hibernate.entity.Clientes;

import java.util.List;

public interface CrudRepository <T>{
    List<T> listar();
    T porid(Long id);

    void guardar(T t);

    void eliminar (Long Id);

    void actualizar(T producto);
}

