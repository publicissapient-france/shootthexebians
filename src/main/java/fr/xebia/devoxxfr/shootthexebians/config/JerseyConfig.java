package fr.xebia.devoxxfr.shootthexebians.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("fr.xebia.devoxxfr.shootthexebians");
        register(JacksonJsonProvider.class);
    }
}
