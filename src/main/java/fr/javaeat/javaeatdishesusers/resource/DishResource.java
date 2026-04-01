package fr.javaeat.javaeatdishesusers.resource;

import fr.javaeat.javaeatdishesusers.model.Dish;
import fr.javaeat.javaeatdishesusers.service.DishService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/dishes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DishResource {

    @Inject
    private DishService dishService;

    @GET
    public List<Dish> getDishes() {
        return dishService.getAllDishes();
    }

    @GET
    @Path("/{id}")
    public Response getDish(@PathParam("id") int id) {
        Dish dish = dishService.getDishById(id);
        if (dish != null) {
            return Response.ok(dish).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addDish(Dish dish) {
        return dishService.createDish(dish) ?
                Response.status(Response.Status.CREATED).entity(dish).build() :
                Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateDish(@PathParam("id") int id, Dish dish) {
        return dishService.updateDish(id, dish) ?
                Response.ok(dish).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDish(@PathParam("id") int id) {
        return dishService.deleteDish(id) ?
                Response.noContent().build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }
}