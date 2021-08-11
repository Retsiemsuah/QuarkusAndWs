package de.scheffler.endpoint.impl.bowlinggame.requestfactory;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;

import javax.enterprise.context.ApplicationScoped;

import java.util.UUID;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

@ApplicationScoped
public class BowlingGameEndpointRequestResponseFactoryImpl implements BowlingGameEndpointRequestResponseFactory{

    private RestAssuredConfig config(){
        return RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 100000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 100000));
    }
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

    @Override
    public Response addThrowToGame(int throwValue, UUID gameId) {
        return given().config(config())
                .with()
                .pathParam("throwValue", throwValue)
                .pathParam("uniquePlayerName", gameId)
                .when()
                .post("/bowling/addNewThrowToGame/{throwValue}/{uniquePlayerName}/");
    }
}
