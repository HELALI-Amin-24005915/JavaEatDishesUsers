package fr.javaeat.javaeatdishesusers.model;

/**
 * Represents a user domain entity in the Model layer of the layered architecture.
 * This class stores user attributes transported between REST resources,
 * business services, and persistence repositories.
 */
public class User {
    private String id;
    private String name;
    private String email;
    private String password;

    /**
     * Creates an empty user instance.
     */
    public User() {}

    /**
     * Creates a fully initialized user instance.
     *
     * @param id unique identifier of the user
     * @param name display name of the user
     * @param email email address of the user
     * @param password persisted password value of the user
     */
    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return user identifier
     */
    public String getId() { return id; }

    /**
     * Returns the display name of the user.
     *
     * @return user name
     */
    public String getName() { return name; }

    /**
     * Returns the email address of the user.
     *
     * @return user email address
     */
    public String getEmail() { return email; }

    /**
     * Returns the persisted password value of the user.
     *
     * @return user password
     */
    public String getPassword() { return password; }

    /**
     * Updates the unique identifier of the user.
     *
     * @param id new user identifier
     */
    public void setId(String id) { this.id = id; }

    /**
     * Updates the display name of the user.
     *
     * @param name new user name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Updates the email address of the user.
     *
     * @param email new user email address
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Updates the persisted password value of the user.
     *
     * @param password new user password
     */
    public void setPassword(String password) { this.password = password; }
}