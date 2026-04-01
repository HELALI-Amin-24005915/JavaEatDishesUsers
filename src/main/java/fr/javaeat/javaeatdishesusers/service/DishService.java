package fr.javaeat.javaeatdishesusers.service;

import fr.javaeat.javaeatdishesusers.model.Dish;
import fr.javaeat.javaeatdishesusers.repository.DishRepositoryInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

/**
 * Implements business logic for dish operations in the Service layer.
 * This service validates domain rules and delegates persistence work
 * to the Repository layer.
 */
@ApplicationScoped
public class DishService {

    @Inject
    private DishRepositoryInterface dishRepository;

    /**
     * Retrieves all dishes available in the system.
     *
     * @return list of dishes returned by the repository
     */
    public List<Dish> getAllDishes() {
        return dishRepository.getAllDishes();
    }

    /**
     * Retrieves one dish by identifier.
     *
     * @param id identifier of the dish to retrieve
     * @return matching dish instance, or {@code null} if it does not exist
     */
    public Dish getDishById(int id) {
        return dishRepository.getDishById(id);
    }

    /**
     * Creates a new dish after validating business constraints.
     *
     * @param dish dish payload to create
     * @return {@code true} when creation succeeds, otherwise {@code false}
     */
    public boolean createDish(Dish dish) {
        if (dish.getPrice() <= 0) {
            System.err.println("Le prix du plat doit être strictement positif.");
            return false;
        }
        return dishRepository.createDish(dish);
    }

    /**
     * Updates an existing dish after validating business constraints.
     *
     * @param id identifier of the dish to update
     * @param dish updated dish payload
     * @return {@code true} when update succeeds, otherwise {@code false}
     */
    public boolean updateDish(int id, Dish dish) {
        if (dish.getPrice() <= 0) {
            return false;
        }
        return dishRepository.updateDish(id, dish);
    }

    /**
     * Deletes an existing dish by identifier.
     *
     * @param id identifier of the dish to delete
     * @return {@code true} when deletion succeeds, otherwise {@code false}
     */
    public boolean deleteDish(int id) {
        return dishRepository.deleteDish(id);
    }
}