package fr.javaeat.javaeatdishesusers.repository;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides centralized MariaDB connection management for the Repository layer.
 * Repository implementations use this utility to access the underlying database.
 */
public class DatabaseConnection {
    private static Connection connection = null;

    /**
     * Prevents instantiation of this utility class.
     */
    private DatabaseConnection() {}

    /**
     * Returns an active MariaDB connection.
     * If no valid connection exists, this method attempts to create one
     * using environment-backed database configuration.
     *
     * @return an open SQL connection, or {@code null} when connection setup fails
     */
    public static Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }

            Class.forName("org.mariadb.jdbc.Driver");

            Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
            String url = dotenv.get("DB_URL") != null ? dotenv.get("DB_URL") : System.getProperty("DB_URL");
            String user = dotenv.get("DB_USER") != null ? dotenv.get("DB_USER") : System.getProperty("DB_USER");
            String password = dotenv.get("DB_PASSWORD") != null ? dotenv.get("DB_PASSWORD") : System.getProperty("DB_PASSWORD");

            if (url == null || user == null) {
                System.err.println("Le fichier .env n'est pas lu par GlassFish.");
                return null;
            }


            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connexion MariaDB réussie !");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}