package fr.javaeat.javaeatdishesusers.resource;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configures the JAX-RS application root in the Resource layer.
 * This bootstrap class activates REST resources under the {@code /api} base path.
 */
@ApplicationPath("/api")
public class JavaEatApplication extends Application {
}