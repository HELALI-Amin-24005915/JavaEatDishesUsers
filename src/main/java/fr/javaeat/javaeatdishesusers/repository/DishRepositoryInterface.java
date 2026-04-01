package fr.javaeat.javaeatdishesusers.repository;

import fr.javaeat.javaeatdishesusers.model.Dish;
import java.util.List;

/**
 * Declares dish persistence operations for the Repository layer.
 * Implementations provide MariaDB data access for dish entities.
 */
public interface DishRepositoryInterface {
    /**
     * Retrieves all dishes from persistence storage.
     *
     * @return list of all persisted dishes
     */
    List<Dish> getAllDishes();

    /**
     * Retrieves one dish by its identifier.
     *
     * @param id identifier of the dish to retrieve
     * @return matching dish instance, or {@code null} when not found
     */
    Dish getDishById(int id);

    /**
     * Persists a new dish in storage.
     *
     * @param dish dish entity to create
     * @return {@code true} when insertion succeeds, otherwise {@code false}
     */
    boolean createDish(Dish dish);

    /**
     * Updates an existing dish in storage.
     *
     * @param id identifier of the dish to update
     * @param dish source data used for the update
     * @return {@code true} when update succeeds, otherwise {@code false}
     */
    boolean updateDish(int id, Dish dish);

    /**
     * Deletes a dish from storage by identifier.
     *
     * @param id identifier of the dish to delete
     * @return {@code true} when deletion succeeds, otherwise {@code false}
     */
    boolean deleteDish(int id);
}