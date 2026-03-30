package fr.javaeat.javaeatdishesusers.service;

import fr.javaeat.javaeatdishesusers.model.User;
import fr.javaeat.javaeatdishesusers.repository.UserRepositoryInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepositoryInterface userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(String id) {
        return userRepository.getUserById(id);
    }

    public boolean createUser(User user) {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            System.err.println("Format d'email invalide.");
            return false;
        }
        return userRepository.createUser(user);
    }

    public boolean updateUser(String id, User user) {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            return false;
        }
        return userRepository.updateUser(id, user);
    }

    public boolean deleteUser(String id) {
        return userRepository.deleteUser(id);
    }
}