package fr.xebia.devoxxfr.shootthexebians.business;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.ok;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("scores")
@Component
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class ScoresResource {

    @Autowired
    private MongoCollection scoreCollection;

    @POST
    public Response createScore(Score score) {
        scoreCollection.save(score);
        return ok().status(CREATED).build();
    }
}
