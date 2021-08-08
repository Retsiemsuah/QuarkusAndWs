package de.scheffler.endpoint.api.bowlinggame;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;


@Path("/bowling")
public interface BowlingGameEndpoint {

    /**
     * creates a new Game and returns the unique id of the game
     * @return unique UUID of the new game
     */
    @POST
    @Path("/newGame")
    Response createNewGame();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/findGameById/{gameId}")
    Response findById(@PathParam("gameId") UUID gameId);

    @POST
    @Path("/addNewPlayer/{uniqueGameId}/{uniquePlayerName}/")
    Response addNewPlayerToGame(
            @PathParam("uniqueGameId") UUID uniqueGameId,
            @PathParam("uniquePlayerName") UUID playerId);

    @GET
    @Path("/startGame/{uniqueGameId}")
    Response startGame(@PathParam("uniqueGameId") UUID gameId);

    @POST
    @Path("/addNewThrowToGame/{throwValue}/{uniqueGameId}/")
    Response addThrowToGame(
            @PathParam("throwValue") int throwValue,
            @PathParam("uniqueGameId") UUID uniqueGameId);
}
