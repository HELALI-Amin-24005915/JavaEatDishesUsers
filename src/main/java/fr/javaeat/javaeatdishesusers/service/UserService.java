package fr.javaeat.javaeatdishesusers.service;

import fr.javaeat.javaeatdishesusers.model.User;
import fr.javaeat.javaeatdishesusers.repository.UserRepositoryInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

/**
 * Implements business logic for user operations in the Service layer.
 * This service validates user-specific rules and delegates persistence actions
 * to the Repository layer.
 */
@ApplicationScoped
public class UserService {

    @Inject
    private UserRepositoryInterface userRepository;

    /**
     * Retrieves all users available in the system.
     *
     * @return list of users returned by the repository
     */
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    /**
     * Retrieves one user by identifier.
     *
     * @param id identifier of the user to retrieve
     * @return matching user instance, or {@code null} if it does not exist
     */
    public User getUserById(String id) {
        return userRepository.getUserById(id);
    }

    /**
     * Creates a new user after validating business constraints.
     *
     * @param user user payload to create
     * @return {@code true} when creation succeeds, otherwise {@code false}
     */
    public boolean createUser(User user) {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            System.err.println("Format d'email invalide.");
            return false;
        }
        return userRepository.createUser(user);
    }

    /**
     * Updates an existing user after validating business constraints.
     *
     * @param id identifier of the user to update
     * @param user updated user payload
     * @return {@code true} when update succeeds, otherwise {@code false}
     */
    public boolean updateUser(String id, User user) {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            return false;
        }
        return userRepository.updateUser(id, user);
    }

    /**
     * Deletes an existing user by identifier.
     *
     * @param id identifier of the user to delete
     * @return {@code true} when deletion succeeds, otherwise {@code false}
     */
    public boolean deleteUser(String id) {
        return userRepository.deleteUser(id);
    }
}