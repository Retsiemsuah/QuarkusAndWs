package de.scheffler.endpoint.impl.bowlinggame;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import javax.enterprise.context.ApplicationScoped;

import static io.restassured.RestAssured.given;

@ApplicationScoped
public class BowlingGameEndpointRequestResponseFactoryImpl implements BowlingGameEndpointRequestResponseFactory{

    @Override
    public Response getNewBowlingGameResponse() {
        return RestAssured.given()
                .when()
                .get("/bowling/newGame");
    }

    @Override
    public Response addNewPlayerToGame(String gameId, String uniquePlayerName) {
        return RestAssured.given()
                .with()
                    .pathParam("uniqueGameId", gameId)
                    .pathParam("uniquePlayerName", uniquePlayerName)
                .when()
                    .post("/bowling/addNewPlayer/{uniqueGameId}/{uniquePlayerName}");
    }

    @Override
    public Response getFindBowlingGameByIdResponse(long gameId) {
        return  RestAssured.given()
                .with()
                    .pathParam("gameId", gameId)
                .when()
                    .get("/bowling/findGameById/{gameId}");
    }
}
