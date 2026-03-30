package fr.javaeat.javaeatdishesusers.repository;

import fr.javaeat.javaeatdishesusers.model.Dish;
import java.util.List;

public interface DishRepositoryInterface {
    List<Dish> getAllDishes();

    Dish getDishById(int id);

    boolean createDish(Dish dish);

    boolean updateDish(int id, Dish dish);

    boolean deleteDish(int id);
}