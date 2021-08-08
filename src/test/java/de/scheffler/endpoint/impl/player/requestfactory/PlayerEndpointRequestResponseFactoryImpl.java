package de.scheffler.endpoint.impl.player.requestfactory;

import de.scheffler.endpoint.api.player.dto.PlayerDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@ApplicationScoped
public class PlayerEndpointRequestResponseFactoryImpl implements PlayerEndpointRequestResponseFactory {

    @Override
    public Response createNewPlayerResponse(PlayerDto playerDto) {
        return given()
                .when()
                .contentType("application/json")
                .body(playerDto)
                .post("/player/newPlayer");
    }

    @Override
    public Response findByIdResponse(UUID playerId) {
        return given()
                .with()
                    .pathParam("id", playerId)
                .when()
                    .get("/player/findPlayer/{id}");
    }
}
