package de.scheffler.endpoint.impl.bowlinggame;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class BowlingGameEndpoint_NewGame {

    @Inject
    BowlingGameEndpointRequestResponseFactory requestFactory;

    @Test
    void createNewGame() {
        Response responseForNewGameRequest =
                requestFactory.getNewBowlingGameResponse();

        assertEquals(responseForNewGameRequest.statusCode(), HttpStatus.SC_OK);
        String firstGameId = responseForNewGameRequest.getBody().toString();
        assertNotNull(firstGameId);
    }

    @Test
    void createMultipleGames_IdIsUniquew() {
        Response responseForNewGameRequest =  requestFactory.getNewBowlingGameResponse();
        assertEquals(responseForNewGameRequest.statusCode(), HttpStatus.SC_OK);
        String firstGameId = responseForNewGameRequest.getBody().toString();
        assertNotNull(firstGameId);

        responseForNewGameRequest =  requestFactory.getNewBowlingGameResponse();
        assertEquals(responseForNewGameRequest.statusCode(), HttpStatus.SC_OK);
        String secondGameId = responseForNewGameRequest.getBody().toString();
        assertNotNull(secondGameId);
        assertNotEquals(firstGameId,secondGameId);
    }
}
