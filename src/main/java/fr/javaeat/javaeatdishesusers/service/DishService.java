package fr.javaeat.javaeatdishesusers.service;

import fr.javaeat.javaeatdishesusers.model.Dish;
import fr.javaeat.javaeatdishesusers.repository.DishRepositoryInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DishService {

    @Inject
    private DishRepositoryInterface dishRepository;

    public List<Dish> getAllDishes() {
        return dishRepository.getAllDishes();
    }

    public Dish getDishById(int id) {
        return dishRepository.getDishById(id);
    }

    public boolean createDish(Dish dish) {
        if (dish.getPrice() <= 0) {
            System.err.println("Le prix du plat doit être strictement positif.");
            return false;
        }
        return dishRepository.createDish(dish);
    }

    public boolean updateDish(int id, Dish dish) {
        if (dish.getPrice() <= 0) {
            return false;
        }
        return dishRepository.updateDish(id, dish);
    }

    public boolean deleteDish(int id) {
        return dishRepository.deleteDish(id);
    }
}