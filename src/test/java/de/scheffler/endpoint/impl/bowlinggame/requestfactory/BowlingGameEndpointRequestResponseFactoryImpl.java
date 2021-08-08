package de.scheffler.endpoint.impl.bowlinggame.requestfactory;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import javax.enterprise.context.ApplicationScoped;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@ApplicationScoped
public class BowlingGameEndpointRequestResponseFactoryImpl implements BowlingGameEndpointRequestResponseFactory{

    @Override
    public Response getNewBowlingGameResponse() {
        return given()
                .when()
                .post("/bowling/newGame");
    }

    @Override
    public Response addNewPlayerToGame(UUID gameId, UUID uniquePlayerName) {
        return given()
                .with()
                    .pathParam("uniqueGameId", gameId)
                    .pathParam("uniquePlayerName", uniquePlayerName)
                .when()
                    .post("/bowling/addNewPlayer/{uniqueGameId}/{uniquePlayerName}");
    }

    @Override
    public Response findBowlingGameByIdResponse(UUID gameId) {
        return  given()
                .with()
                    .pathParam("gameId", gameId)
                .when()
                    .get("/bowling/findGameById/{gameId}");
    }

    @Override
    public Response startBowlingGameResponse(UUID gameId) {
        return  given()
                .with()
                .pathParam("gameId", gameId)
                .when()
                .get("/bowling/startGame/{gameId}");
    }
}
