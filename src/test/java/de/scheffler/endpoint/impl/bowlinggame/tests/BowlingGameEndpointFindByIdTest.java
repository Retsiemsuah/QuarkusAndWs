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

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class BowlingGameEndpointFindByIdTest {

    @Inject
    BowlingGameEndpointRequestResponseFactory requestResponseFactory;

    @Test
    void getSameGameAfterCreationOfNewGame() {
        Response responseForFirstGameRequest = requestResponseFactory.getNewBowlingGameResponse();
        UUID firstGameId = responseForFirstGameRequest.getBody().as(UUID.class);
        assertEquals(HttpStatus.SC_OK,responseForFirstGameRequest.statusCode());
        assertNotNull(firstGameId);

        Response responseForFindGameById = requestResponseFactory.findBowlingGameByIdResponse(firstGameId);
        assertEquals(HttpStatus.SC_OK,responseForFindGameById.statusCode());

        BowlingGameDto gameDto = requestResponseFactory.findBowlingGameByIdResponse(firstGameId).body().as(BowlingGameDto.class);
        assertEquals(firstGameId.toString(),gameDto.getGameId());
    }
}
