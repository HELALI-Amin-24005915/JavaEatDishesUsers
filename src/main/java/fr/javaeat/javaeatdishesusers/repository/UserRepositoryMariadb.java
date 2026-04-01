package fr.javaeat.javaeatdishesusers.repository;

import fr.javaeat.javaeatdishesusers.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MariaDB-backed implementation of user persistence operations in the Repository layer.
 * This component executes SQL statements and maps database rows to user model objects.
 */
@ApplicationScoped
public class UserRepositoryMariadb implements UserRepositoryInterface {

    /**
     * Retrieves all users from the MariaDB {@code users} table.
     *
     * @return list of user entities, or an empty list when no rows are found
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getString("password")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return users;
    }

    /**
     * Retrieves one user from MariaDB by identifier.
     *
     * @param id identifier of the user to load
     * @return matching user entity, or {@code null} when no row matches
     */
    @Override
    public User getUserById(String id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    /**
     * Inserts a new user row in MariaDB.
     *
     * @param user user data to persist
     * @return {@code true} when the insertion affects at least one row
     */
    @Override
    public boolean createUser(User user) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (id, name, email, password) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    /**
     * Updates an existing user row in MariaDB.
     *
     * @param id identifier of the user row to update
     * @param user updated user data
     * @return {@code true} when at least one row is updated
     */
    @Override
    public boolean updateUser(String id, User user) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?")) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    /**
     * Deletes a user row from MariaDB.
     *
     * @param id identifier of the user row to delete
     * @return {@code true} when at least one row is deleted
     */
    @Override
    public boolean deleteUser(String id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}