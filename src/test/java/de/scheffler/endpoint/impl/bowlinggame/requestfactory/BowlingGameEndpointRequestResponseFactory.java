package de.scheffler.endpoint.impl.bowlinggame.requestfactory;

import io.restassured.response.Response;

import java.util.UUID;

public interface BowlingGameEndpointRequestResponseFactory {
    Response getNewBowlingGameResponse();

    Response addNewPlayerToGame(UUID gameId, UUID uniquePlayerName);

    Response findBowlingGameByIdResponse(UUID gameId);

    Response startBowlingGameResponse(UUID gameId);

    Response addThrowToGame(int throwValue, UUID gameId);
}
