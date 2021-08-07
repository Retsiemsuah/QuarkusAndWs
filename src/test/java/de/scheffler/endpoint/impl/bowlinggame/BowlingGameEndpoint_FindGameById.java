package de.scheffler.endpoint.impl.bowlinggame;

import de.scheffler.data.api.bowling.game.BowlingGame;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
class BowlingGameEndpoint_FindGameById {
    @Inject
    BowlingGameEndpointRequestResponseFactory requestResponseFactory;

    @Test
    void requestOnEmptyDb() {
        Response game = requestResponseFactory.getFindBowlingGameByIdResponse(1L);
        assertNull(game);
    }

    @Test
    void getSameGameAfterCreationOfNewGame() {
        Response responseForNewGameRequest = requestResponseFactory.getNewBowlingGameResponse();
        Long firstGameId = Long.valueOf(responseForNewGameRequest.getBody().asString());
        assertNotNull(firstGameId);
        Response game = requestResponseFactory.getFindBowlingGameByIdResponse(firstGameId);
        assertNotNull(game);
//        Assertions.assertEquals(firstGameId, game.id);
    }
}
