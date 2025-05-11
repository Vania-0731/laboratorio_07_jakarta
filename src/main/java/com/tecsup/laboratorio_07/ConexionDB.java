package com.tecsup.laboratorio_07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private String url = "jdbc:mysql://localhost:3306/tienda"; // Cambia esto por tu URL
    private String user = "root";
    private String password = "";
    private String className = "com.mysql.cj.jdbc.Driver"; // Controlador JDBC para MySQL

    public Connection getConnection() throws SQLException {
        try {
            // Cargar el controlador JDBC
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL driver not found", e); // Error si no encuentra el driver
        }

        // Establecer la conexión con la base de datos
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        ConexionDB conexionDB = new ConexionDB();
        try {
            // Intentar obtener la conexión
            Connection conexion = conexionDB.getConnection();
            System.out.println("Conexión exitosa a la base de datos!");

            // Aquí puedes hacer tus operaciones con la base de datos, por ejemplo, consultas

            conexion.close(); // No olvides cerrar la conexión cuando termines
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
