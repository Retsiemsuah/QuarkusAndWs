package de.scheffler.endpoint.api.player;

import de.scheffler.endpoint.api.player.dto.PlayerDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/player")
public interface PlayerEndpoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/newPlayer")
    Response createNewPlayer(PlayerDto player);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/findPlayer/{id}")
    Response findById(@PathParam("id") UUID playerId);
}
