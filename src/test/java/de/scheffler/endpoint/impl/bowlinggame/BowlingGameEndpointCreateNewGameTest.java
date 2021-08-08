package de.scheffler.endpoint.impl.bowlinggame;

import de.scheffler.endpoint.impl.bowlinggame.requestfactory.BowlingGameEndpointRequestResponseFactory;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class BowlingGameEndpointCreateNewGameTest {

    @Inject
    BowlingGameEndpointRequestResponseFactory requestFactory;

    @Test
    void createNewGame() {
        Response responseForNewGameRequest = requestFactory.getNewBowlingGameResponse();

        assertEquals(HttpStatus.SC_OK,responseForNewGameRequest.statusCode());
        String firstGameId = responseForNewGameRequest.getBody().toString();
        assertNotNull(firstGameId);
    }

    @Test
    void createMultipleGames_IdIsUnique() {
        Response responseForNewGameRequest = requestFactory.getNewBowlingGameResponse();
        assertEquals(HttpStatus.SC_OK,responseForNewGameRequest.statusCode());
        UUID firstGameId = responseForNewGameRequest.getBody().as(UUID.class);
        assertNotNull(firstGameId);

        responseForNewGameRequest =  requestFactory.getNewBowlingGameResponse();
        assertEquals(HttpStatus.SC_OK,responseForNewGameRequest.statusCode());
        UUID secondGameId = responseForNewGameRequest.getBody().as(UUID.class);
        assertNotNull(secondGameId);
        assertNotEquals(firstGameId,secondGameId);
    }
}
