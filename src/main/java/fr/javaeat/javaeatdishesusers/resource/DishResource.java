package fr.javaeat.javaeatdishesusers.resource;

import fr.javaeat.javaeatdishesusers.model.Dish;
import fr.javaeat.javaeatdishesusers.service.DishService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Exposes REST endpoints for dish operations in the Resource layer.
 * This resource handles HTTP requests and delegates business processing
 * to the DishService.
 */
@Path("/dishes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DishResource {

    @Inject
    private DishService dishService;

    /**
     * Handles HTTP GET requests to retrieve all dishes.
     *
     * @return list of available dishes as JSON payload
     */
    @GET
    public List<Dish> getDishes() {
        return dishService.getAllDishes();
    }

    /**
     * Handles HTTP GET requests to retrieve one dish by identifier.
     *
     * @param id dish identifier extracted from the request path
     * @return HTTP 200 with the dish when found, otherwise HTTP 404
     */
    @GET
    @Path("/{id}")
    public Response getDish(@PathParam("id") int id) {
        Dish dish = dishService.getDishById(id);
        if (dish != null) {
            return Response.ok(dish).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Handles HTTP POST requests to create a new dish.
     *
     * @param dish request body containing dish data
     * @return HTTP 201 when creation succeeds, otherwise HTTP 400
     */
    @POST
    public Response addDish(Dish dish) {
        return dishService.createDish(dish) ?
                Response.status(Response.Status.CREATED).entity(dish).build() :
                Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * Handles HTTP PUT requests to update an existing dish.
     *
     * @param id dish identifier extracted from the request path
     * @param dish request body containing updated dish data
     * @return HTTP 200 when update succeeds, otherwise HTTP 404
     */
    @PUT
    @Path("/{id}")
    public Response updateDish(@PathParam("id") int id, Dish dish) {
        return dishService.updateDish(id, dish) ?
                Response.ok(dish).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Handles HTTP DELETE requests to remove a dish.
     *
     * @param id dish identifier extracted from the request path
     * @return HTTP 204 when deletion succeeds, otherwise HTTP 404
     */
    @DELETE
    @Path("/{id}")
    public Response deleteDish(@PathParam("id") int id) {
        return dishService.deleteDish(id) ?
                Response.noContent().build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }
}