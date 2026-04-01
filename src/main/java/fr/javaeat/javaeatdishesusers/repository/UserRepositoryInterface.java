package fr.javaeat.javaeatdishesusers.repository;

import fr.javaeat.javaeatdishesusers.model.User;
import java.util.List;

/**
 * Declares user persistence operations for the Repository layer.
 * Implementations provide MariaDB data access for user entities.
 */
public interface UserRepositoryInterface {
    /**
     * Retrieves all users from persistence storage.
     *
     * @return list of all persisted users
     */
    List<User> getAllUsers();

    /**
     * Retrieves one user by identifier.
     *
     * @param id identifier of the user to retrieve
     * @return matching user instance, or {@code null} when not found
     */
    User getUserById(String id);

    /**
     * Persists a new user in storage.
     *
     * @param user user entity to create
     * @return {@code true} when insertion succeeds, otherwise {@code false}
     */
    boolean createUser(User user);

    /**
     * Updates an existing user in storage.
     *
     * @param id identifier of the user to update
     * @param user source data used for the update
     * @return {@code true} when update succeeds, otherwise {@code false}
     */
    boolean updateUser(String id, User user);

    /**
     * Deletes a user from storage by identifier.
     *
     * @param id identifier of the user to delete
     * @return {@code true} when deletion succeeds, otherwise {@code false}
     */
    boolean deleteUser(String id);
}