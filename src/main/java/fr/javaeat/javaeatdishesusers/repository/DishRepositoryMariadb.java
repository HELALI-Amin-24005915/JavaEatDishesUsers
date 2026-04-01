package fr.javaeat.javaeatdishesusers.repository;

import fr.javaeat.javaeatdishesusers.model.Dish;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MariaDB-backed implementation of dish persistence operations in the Repository layer.
 * This component executes SQL queries and maps result sets to dish model objects.
 */
@ApplicationScoped
public class DishRepositoryMariadb implements DishRepositoryInterface {

    /**
     * Retrieves all dishes from the MariaDB {@code dishes} table.
     *
     * @return list of dish entities, or an empty list when no rows are found
     */
    @Override
    public List<Dish> getAllDishes() {
        List<Dish> dishes = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM dishes";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Mapping SQL -> Objet Java
                Dish dish = new Dish(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price")
                );
                dishes.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dishes;
    }

    /**
     * Retrieves one dish from MariaDB by primary key.
     *
     * @param id identifier of the dish to load
     * @return matching dish entity, or {@code null} when no row matches
     */
    @Override
    public Dish getDishById(int id) {
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM dishes WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Dish(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserts a new dish row in MariaDB.
     *
     * @param dish dish data to persist
     * @return {@code true} when the insertion affects at least one row
     */
    @Override
    public boolean createDish(Dish dish) {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO dishes (name, description, price) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, dish.getName());
            stmt.setString(2, dish.getDescription());
            stmt.setDouble(3, dish.getPrice());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing dish row in MariaDB.
     *
     * @param id identifier of the dish row to update
     * @param dish updated dish data
     * @return {@code true} when at least one row is updated
     */
    @Override
    public boolean updateDish(int id, Dish dish) {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE dishes SET name = ?, description = ?, price = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, dish.getName());
            stmt.setString(2, dish.getDescription());
            stmt.setDouble(3, dish.getPrice());
            stmt.setInt(4, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a dish row from MariaDB.
     *
     * @param id identifier of the dish row to delete
     * @return {@code true} when at least one row is deleted
     */
    @Override
    public boolean deleteDish(int id) {
        Connection conn = DatabaseConnection.getConnection();
        String query = "DELETE FROM dishes WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}