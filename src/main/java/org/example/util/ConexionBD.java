package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {


    private static final String URL = "jdbc:mysql://localhost:3307/carros";
    private static final String USUARIO = "root";

    private static final String CONTRASENA = "123456";

    public static Connection obtenerConexion() throws SQLException {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

            System.out.println("✅ Conexión establecida con el contenedor 'carros' en el puerto 3307");
            return con;

        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error: No se encontró el Driver de MySQL en el proyecto.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: Verifica que Docker esté corriendo y el puerto 3307 esté libre.");
            e.printStackTrace();
            return null;
        }
    }
}
