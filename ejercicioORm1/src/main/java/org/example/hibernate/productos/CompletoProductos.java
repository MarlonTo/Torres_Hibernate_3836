package org.example.hibernate.productos;

import jakarta.persistence.EntityManager;
import org.example.hibernate.entity.Productos;
import org.example.hibernate.util.JpaUtil;

import org.example.service.ProductoService;
import org.example.service.ProductoServiceImplementa;

import java.util.Optional;

public class CompletoProductos {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        ProductoService service = new ProductoServiceImplementa(em);
        System.out.println("==============================Listado de productos==============================");
        service.listar().forEach(System.out::println);
        System.out.println("==============================Buscar producto por id==============================");
        System.out.println(service.buscar(1L));
        System.out.println("==============================Guardar producto==============================");
        Optional<Productos> productos = Optional.of(new Productos());
        productos.get().setNombre("Coca Cola");
        productos.get().setPrecio(1.5F);
        productos.get().setPuntuacion(5);
        service.guardar(productos.orElse(null));
        service.listar().forEach(System.out::println);
        System.out.println("==============================Actualizar producto==============================");
        productos = service.buscar(1L);
        productos.get().setNombre("Pepsi");
        productos.get().setPrecio(1.8F);
        productos.get().setPuntuacion(4);
        service.actualizar(productos.orElse(null));
        service.listar().forEach(System.out::println);
        System.out.println("==============================Eliminar producto==============================");
        service.eliminar(6L);
        service.listar().forEach(System.out::println);

    }
}
