package main.java.com.javatesting.kinalproyect.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de conexión a MySQL implementada con el patrón Singleton:
 * toda la aplicación comparte una única instancia de conexión,
 * obtenida siempre a través de getInstance().
 */
public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/abarroteria_kinal_in4bv";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Constructor privado: nadie fuera de esta clase puede crear una instancia.
    private DBConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    // Punto único de acceso a la conexión.
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
