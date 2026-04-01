package fr.javaeat.javaeatdishesusers.resource;

import fr.javaeat.javaeatdishesusers.model.User;
import fr.javaeat.javaeatdishesusers.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Exposes REST endpoints for user operations in the Resource layer.
 * This resource handles HTTP requests and delegates business processing
 * to the UserService.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserService userService;

    /**
     * Handles HTTP GET requests to retrieve all users.
     *
     * @return list of available users as JSON payload
     */
    @GET
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    /**
     * Handles HTTP GET requests to retrieve one user by identifier.
     *
     * @param id user identifier extracted from the request path
     * @return HTTP 200 with the user when found, otherwise HTTP 404
     */
    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") String id) {
        User user = userService.getUserById(id);
        return (user != null) ? Response.ok(user).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Handles HTTP POST requests to create a new user.
     *
     * @param user request body containing user data
     * @return HTTP 201 when creation succeeds, otherwise HTTP 400
     */
    @POST
    public Response addUser(User user) {
        return userService.createUser(user) ? Response.status(Response.Status.CREATED).entity(user).build() : Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * Handles HTTP PUT requests to update an existing user.
     *
     * @param id user identifier extracted from the request path
     * @param user request body containing updated user data
     * @return HTTP 200 when update succeeds, otherwise HTTP 404
     */
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id, User user) {
        return userService.updateUser(id, user) ?
                Response.ok(user).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Handles HTTP DELETE requests to remove a user.
     *
     * @param id user identifier extracted from the request path
     * @return HTTP 204 when deletion succeeds, otherwise HTTP 404
     */
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        return userService.deleteUser(id) ?
                Response.noContent().build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }
}