package de.scheffler.endpoint.api.bowlinggame;

import de.scheffler.data.api.player.Player;
import de.scheffler.endpoint.api.bowlinggame.dto.BowlingGameDto;

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
    @GET
    @Path("/newGame")
    Response createNewGame();

    @POST
    @Path("/addNewPlayer/{uniqueGameId}/{uniquePlayerName}/")
    Response newPlayer(
            @PathParam("uniqueGameId") UUID uniqueGameId,
            @PathParam("uniquePlayerName") UUID playerId);

    @GET
    @Path("/startGame/{uniqueGameId}")
    Response startGame(@PathParam("uniqueGameId") UUID gameId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/findGameById/{gameId}")
    BowlingGameDto findById(@PathParam("gameId") UUID gameId);

    @GET
    @Path("/findCurrentPlayer/{uniqueGameId}")
    Player getCurrentPlayerBy(
            @PathParam("uniqueGameId") UUID uniqueGameId);
}
