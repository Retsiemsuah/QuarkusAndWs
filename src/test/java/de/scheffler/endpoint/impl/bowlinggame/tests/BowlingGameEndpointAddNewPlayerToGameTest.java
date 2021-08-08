package de.scheffler.endpoint.impl.bowlinggame.tests;

import de.scheffler.data.api.bowling.game.BowlingGameState;
import de.scheffler.endpoint.api.bowlinggame.dto.BowlingGameDto;
import de.scheffler.endpoint.api.bowlinggame.dto.BowlingGameEndpointMessages;
import de.scheffler.endpoint.api.player.dto.PlayerDto;
import de.scheffler.endpoint.impl.bowlinggame.requestfactory.BowlingGameEndpointRequestResponseFactory;
import de.scheffler.endpoint.impl.player.requestfactory.PlayerDtoBuilder;
import de.scheffler.endpoint.impl.player.requestfactory.PlayerEndpointRequestResponseFactory;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class BowlingGameEndpointAddNewPlayerToGameTest {

    @Inject
    BowlingGameEndpointRequestResponseFactory bowlingRequestFactory;

    @Inject
    PlayerEndpointRequestResponseFactory playerRequestFactory;

    UUID gameId;

    @BeforeEach
    void createNewGame(){
        gameId = bowlingRequestFactory.getNewBowlingGameResponse().getBody().as(UUID.class);
    }

    @Test
    void newPlayer() {
        String playerFranz = "Franz";
        String playerBob = "Bob";
        assertNotNull(gameId);
        UUID playerFranzId = playerRequestFactory.createNewPlayerResponse(new PlayerDtoBuilder().newPlayerDto().withDisplayName(playerFranz).build()).as(UUID.class);
        UUID playerBobId = playerRequestFactory.createNewPlayerResponse(new PlayerDtoBuilder().newPlayerDto().withDisplayName(playerBob).build()).as(UUID.class);

        Response responseForAddingFranz = bowlingRequestFactory.addNewPlayerToGame(gameId, playerFranzId);
        assertEquals(HttpStatus.SC_OK, responseForAddingFranz.statusCode());

        Response responseForAddingBob = bowlingRequestFactory.addNewPlayerToGame(gameId, playerBobId);
        assertEquals(HttpStatus.SC_OK, responseForAddingBob.statusCode());

        Response gameWithPlayersResponse =bowlingRequestFactory.findBowlingGameByIdResponse(gameId);
        assertEquals(HttpStatus.SC_OK,gameWithPlayersResponse.statusCode());

        BowlingGameDto gameDto = gameWithPlayersResponse.body().as(BowlingGameDto.class);
        assertEquals(gameId.toString(), gameDto.getGameId());
        assertEquals(2,gameDto.getGameRunDtoArrayList().size());
        assertEquals(BowlingGameState.NOT_STARTED,gameDto.getGameState());
    }
}