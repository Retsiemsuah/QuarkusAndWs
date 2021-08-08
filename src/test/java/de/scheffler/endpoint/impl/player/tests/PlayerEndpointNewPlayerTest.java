package de.scheffler.endpoint.impl.player.tests;

import de.scheffler.endpoint.api.player.dto.PlayerDto;
import de.scheffler.endpoint.impl.player.requestfactory.PlayerDtoBuilder;
import de.scheffler.endpoint.impl.player.requestfactory.PlayerEndpointRequestResponseFactory;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PlayerEndpointNewPlayerTest {

    @Inject
    PlayerEndpointRequestResponseFactory requestResponseFactory;

    @Test
    void newPlayer() {
        PlayerDto playerDto = new PlayerDtoBuilder().newPlayerDto().withDisplayName("Franz Scheffler").build();
        Response responseFirstAddNewPlayerRequest = requestResponseFactory.createNewPlayerResponse(playerDto);
        UUID playerId1 = responseFirstAddNewPlayerRequest.body().as(UUID.class);
        assertEquals(SC_OK, responseFirstAddNewPlayerRequest.statusCode());
        assertNotNull(playerId1);

        Response responseForSecondPlayerRequest = requestResponseFactory.createNewPlayerResponse(playerDto);
        UUID playerId2 = responseForSecondPlayerRequest.body().as(UUID.class);
        assertEquals(SC_OK, responseForSecondPlayerRequest.statusCode());
        assertNotNull(playerId1);
        assertNotEquals(playerId1,playerId2);
    }


    @Test
    void findValuesForCorrectPlayers() {
        String namePlayerFranz ="Franz Scheffler";
        String namePlayerBob ="Bob Baumeister";

        UUID playerFranzId =
                requestResponseFactory
                        .createNewPlayerResponse(
                                new PlayerDtoBuilder()
                                .newPlayerDto()
                                        .withDisplayName(namePlayerFranz)
                                        .build())
                        .body()
                        .as(UUID.class);

        UUID playerBobId =
                requestResponseFactory
                        .createNewPlayerResponse(
                                new PlayerDtoBuilder()
                                        .newPlayerDto()
                                        .withDisplayName(namePlayerBob)
                                        .build())
                        .body()
                        .as(UUID.class);

        PlayerDto returnedBobDto = requestResponseFactory.findByIdResponse(playerBobId).body().as(PlayerDto.class);
        assertNotNull(namePlayerBob);
        assertEquals(namePlayerBob,returnedBobDto.getName());

        PlayerDto returnedFranzDto = requestResponseFactory.findByIdResponse(playerFranzId).body().as(PlayerDto.class);
        assertNotNull(returnedFranzDto);
        assertEquals(namePlayerFranz,returnedFranzDto.getName());
    }
}