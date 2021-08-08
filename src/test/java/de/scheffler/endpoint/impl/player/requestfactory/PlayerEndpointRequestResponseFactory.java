package de.scheffler.endpoint.impl.player.requestfactory;

import de.scheffler.endpoint.api.player.dto.PlayerDto;
import io.restassured.response.Response;

import java.util.UUID;

public interface PlayerEndpointRequestResponseFactory {
    Response createNewPlayerResponse(PlayerDto playerDto);

    Response findByIdResponse(UUID playerId);
}