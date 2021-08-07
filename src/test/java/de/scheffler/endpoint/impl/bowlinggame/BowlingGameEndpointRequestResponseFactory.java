package de.scheffler.endpoint.impl.bowlinggame;

import io.restassured.response.Response;

public interface BowlingGameEndpointRequestResponseFactory {
    Response getNewBowlingGameResponse();

    Response addNewPlayerToGame(String gameId, String uniquePlayerName);

    Response getFindBowlingGameByIdResponse(long gameId);
}
