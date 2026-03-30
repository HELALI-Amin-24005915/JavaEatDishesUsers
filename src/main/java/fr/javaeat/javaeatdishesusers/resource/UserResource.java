package fr.javaeat.javaeatdishesusers.resource;

import fr.javaeat.javaeatdishesusers.model.User;
import fr.javaeat.javaeatdishesusers.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") String id) {
        User user = userService.getUserById(id);
        return (user != null) ? Response.ok(user).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addUser(User user) {
        return userService.createUser(user) ? Response.status(Response.Status.CREATED).entity(user).build() : Response.status(Response.Status.BAD_REQUEST).build();
    }
}