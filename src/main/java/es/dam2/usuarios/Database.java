package es.dam2.usuarios;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Database {
    // Propiedades de la base de datos cargadas desde db.properties
    private static final Properties PROPERTIES = new Properties();
    
    //Se encarga de cargar las propiedades de la base de datos desde el archivo db.properties
    //El bloque static se ejecuta una sola vez en cuanto la JVM carga la clase en memoria.
    static {
        try (InputStream input = Database.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new IllegalStateException("No se encuentra db.properties en src/main/resources.");
            }
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("No se pudo leer db.properties: " + e.getMessage());
        }
    }

    // Constructor privado para evitar instanciación
    private Database() {}

    // Método para obtener una conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        String url = PROPERTIES.getProperty("db.url");
        String user = PROPERTIES.getProperty("db.user");
        String password = PROPERTIES.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }
}
