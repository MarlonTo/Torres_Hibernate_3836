package org.example.jdbc;

import java.sql.*;

public class EjercioJdbc {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/cursoespe?serverTimezone=UTC";
        String usuario = "root";
        String clave = "";
        try{
            Connection conn = DriverManager.getConnection(url, usuario, clave);
            Statement stmt =conn.createStatement();
            ResultSet resultado = stmt.executeQuery("select * from cliente");
            while (resultado.next()) {
                System.out.println(resultado.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
