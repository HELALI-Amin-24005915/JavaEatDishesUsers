package fr.javaeat.javaeatdishesusers.model;

/**
 * Represents a dish domain entity in the Model layer of the layered architecture.
 * This class encapsulates dish data exchanged between the Resource, Service,
 * and Repository layers.
 */
public class Dish {
    private int id;
    private String name;
    private String description;
    private double price;

    /**
     * Creates an empty dish instance.
     */
    public Dish() {}

    /**
     * Creates a fully initialized dish instance.
     *
     * @param id unique identifier of the dish
     * @param name business name of the dish
     * @param description textual description of the dish
     * @param price unit price of the dish
     */
    public Dish(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Returns the unique identifier of the dish.
     *
     * @return dish identifier
     */
    public int getId() { return id; }

    /**
     * Returns the business name of the dish.
     *
     * @return dish name
     */
    public String getName() { return name; }

    /**
     * Returns the textual description of the dish.
     *
     * @return dish description
     */
    public String getDescription() { return description; }

    /**
     * Returns the current unit price of the dish.
     *
     * @return dish price
     */
    public double getPrice() { return price; }

    /**
     * Updates the unique identifier of the dish.
     *
     * @param id new dish identifier
     */
    public void setId(int id) { this.id = id; }

    /**
     * Updates the business name of the dish.
     *
     * @param name new dish name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Updates the textual description of the dish.
     *
     * @param description new dish description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Updates the unit price of the dish.
     *
     * @param price new dish price
     */
    public void setPrice(double price) { this.price = price; }
}