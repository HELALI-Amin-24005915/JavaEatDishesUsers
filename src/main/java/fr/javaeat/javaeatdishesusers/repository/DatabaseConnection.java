package fr.javaeat.javaeatdishesusers.repository;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;
    private DatabaseConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");

                Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
                String url = dotenv.get("DB_URL");
                String user = dotenv.get("DB_USER");
                String password = dotenv.get("DB_PASSWORD");

                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connexion à MariaDB (Alwaysdata) réussie !");

            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Erreur de connexion à la base de données.");
                e.printStackTrace();
            }
        }
        return connection;
    }
}