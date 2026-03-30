package fr.javaeat.javaeatdishesusers.repository;

import fr.javaeat.javaeatdishesusers.model.User;
import java.util.List;

public interface UserRepositoryInterface {
    List<User> getAllUsers();
    User getUserById(String id);
    boolean createUser(User user);
    boolean updateUser(String id, User user);
    boolean deleteUser(String id);
}