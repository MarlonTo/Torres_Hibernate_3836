package org.example.hibernate.ejecuta;

import jakarta.persistence.EntityManager;
import org.example.hibernate.entity.Clientes;
import org.example.service.ClienteService;
import org.example.service.ClienteServiceImplementa;
import org.example.hibernate.util.JpaUtil;

import java.util.List;
import java.util.Optional;

public class Completo {
    public static void main(String[] args) {
         EntityManager em = JpaUtil.getEntityManager();
        ClienteService service = new ClienteServiceImplementa(em);
        System.out.println("==============================Listado de clientes==============================");
        List<Clientes> clientes = service.listar();
        clientes.forEach(System.out::println);
        System.out.println("==============================Buscar cliente por id==============================");
        Optional<Clientes> optional = service.buscar(1L);
        optional.ifPresent(System.out::println);
        System.out.println("==============================Guardar cliente==============================");
        Clientes clientes1 = new Clientes();
        clientes1.setNombre("Lomas");
        clientes1.setApellido("Turbas");
        clientes1.setForma_pago("Transferencia");
        service.guardar(clientes1);
        service.listar().forEach(System.out::println);

        System.out.println("==============================Eliminar cliente==============================");
        service.eliminar(5L);
        service.listar().forEach(System.out::println);
        em.close();
    }

}
