package de.scheffler.endpoint.impl.bowlinggame.tests;

import de.scheffler.data.api.bowling.game.BowlingGameState;
import de.scheffler.endpoint.api.bowlinggame.dto.BowlingGameDto;
import de.scheffler.endpoint.impl.bowlinggame.requestfactory.BowlingGameEndpointRequestResponseFactory;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class BowlingGameEndpointStartGameTest {

    @Inject
    BowlingGameEndpointRequestResponseFactory requestFactory;

    @Test
    void starteGame() {
        Response responseForNewGameRequest = requestFactory.getNewBowlingGameResponse();
        UUID gameId = responseForNewGameRequest.getBody().as(UUID.class);
        BowlingGameDto gameFromServer = requestFactory.findBowlingGameByIdResponse(gameId).body().as(BowlingGameDto.class);
        assertEquals(BowlingGameState.NOT_STARTED,gameFromServer.getGameState());

        //Start game request
        Response response = requestFactory.startBowlingGameResponse(gameId);
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        requestFactory.findBowlingGameByIdResponse(gameId);

        gameFromServer = requestFactory.findBowlingGameByIdResponse(gameId).body().as(BowlingGameDto.class);
        assertEquals(BowlingGameState.RUNNING,gameFromServer.getGameState());
    }
}
