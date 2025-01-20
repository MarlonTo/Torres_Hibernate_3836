package org.example.hibernate.entity;

import jakarta.persistence.*;

@Entity
@Table(name="cliente")
public class Clientes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(name = "forma_pago")
    private String forma_pago;

    public Clientes() {

    }
    public Clientes(String nombre, String apellido, String forma_pago) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.forma_pago = forma_pago;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", forma_pago='" + forma_pago + '\'' +
                '}';
    }
}
