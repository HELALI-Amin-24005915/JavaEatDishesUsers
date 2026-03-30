package fr.javaeat.javaeatdishesusers.repository;

import fr.javaeat.javaeatdishesusers.model.Dish;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DishRepositoryMariadb implements DishRepositoryInterface {

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