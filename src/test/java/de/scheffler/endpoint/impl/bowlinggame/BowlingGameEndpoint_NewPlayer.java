package de.scheffler.endpoint.impl.bowlinggame;

import de.scheffler.endpoint.api.bowlinggame.dto.BowlingGameEndpointMessages;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static de.scheffler.endpoint.api.bowlinggame.dto.BowlingGameEndpointMessages.PLAYER_ADDED_TO_GAME_RESPONSE;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class BowlingGameEndpoint_NewPlayer {

    @Inject
    BowlingGameEndpointRequestResponseFactory requestResponseFactory;

    @Test
    void newPlayer() {
        Response responseForAddNewPlayerRequest = requestResponseFactory.getNewBowlingGameResponse();
        String validGameId = responseForAddNewPlayerRequest.body().asString();
        String uniquePlayerName = "Franz Scheffler";
        Response responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(validGameId, uniquePlayerName);

        assertEquals(SC_OK, responseForNewPlayer.statusCode());
    }

    @Test
    void invalidGameId() {
        String invalidGameId="99999";
        Response responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(invalidGameId, "Max Scheffler");
        assertEquals(SC_BAD_REQUEST, responseForNewPlayer.statusCode());
        assertEquals(String.format(BowlingGameEndpointMessages.NO_VALID_GAME_ID_RESPONSE_TEXT, invalidGameId),responseForNewPlayer.getBody().asString());
    }

    @Test
    void playerAddedSuccessfullToGame() {
        String gameId=requestResponseFactory.getNewBowlingGameResponse().body().asString();
        String uniquePlayerName = "Test Scheffler";
        Response responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(gameId, uniquePlayerName);
        assertEquals(SC_OK, responseForNewPlayer.statusCode() );
        assertEquals(String.format(PLAYER_ADDED_TO_GAME_RESPONSE,uniquePlayerName,gameId),responseForNewPlayer.body().asString());
    }

    @Test
    void twoDifferentPlayerAddedSuccessfullToGame() {
        String gameId=requestResponseFactory.getNewBowlingGameResponse().body().asString();
        String player1 = "Aloha Scheffler";
        Response responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(gameId, player1);
        assertEquals(SC_OK, responseForNewPlayer.statusCode() );
        assertEquals(String.format(PLAYER_ADDED_TO_GAME_RESPONSE,player1,gameId ),responseForNewPlayer.getBody().asString());

        String player2 = "Samson Mustermann";
        responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(gameId, player2);
        assertEquals(SC_OK, responseForNewPlayer.statusCode());
        assertEquals( String.format(PLAYER_ADDED_TO_GAME_RESPONSE,player2,gameId ),responseForNewPlayer.getBody().asString());
    }

    @Test
    void playerIsAlreadyRegistredForGame() {
        String gameId=requestResponseFactory.getNewBowlingGameResponse().body().asString();
        String player1 = "Mara Scheffler";
        Response responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(gameId, player1);
        assertEquals(SC_OK, responseForNewPlayer.statusCode());
        assertEquals( String.format(PLAYER_ADDED_TO_GAME_RESPONSE,player1,gameId ),responseForNewPlayer.getBody().asString());

        responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(gameId, player1);
        assertEquals(SC_BAD_REQUEST, responseForNewPlayer.statusCode());
        assertEquals(String.format(BowlingGameEndpointMessages.PLAYER_ALREADY_ADDED_TO_THE_GAME,player1, gameId ),responseForNewPlayer.getBody().asString());
    }

    @Test
    void moreThanSixPlayersAdded() {
        String gameId= requestResponseFactory.getNewBowlingGameResponse().getBody().toString();
        String player = "Nora Scheffler";
        Response responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(gameId, player);
        assertEquals(SC_OK, responseForNewPlayer.statusCode());

        player = "Max Mustermann";
        responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(gameId, player);
        assertEquals(SC_OK, responseForNewPlayer.statusCode());

        player = "Peter Lustig";
        responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(gameId, player);
        assertEquals(SC_OK, responseForNewPlayer.statusCode());

        player = "Manfred Mann";
        responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(gameId, player);
        assertEquals(SC_OK,responseForNewPlayer.statusCode());

        player = "Martin Fowler";
        responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(gameId, player);
        assertEquals(SC_OK, responseForNewPlayer.statusCode());

        player = "James Jameson";
        responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(gameId, player);
        assertEquals(SC_OK, responseForNewPlayer.statusCode());

        player = "Unable To AddMe";
        responseForNewPlayer = requestResponseFactory.addNewPlayerToGame(gameId, player);
        assertEquals(SC_BAD_REQUEST,responseForNewPlayer.statusCode());
        assertEquals(String.format(BowlingGameEndpointMessages.MAX_PLAYER_LIMIT_ALREADY_REACHED, gameId),responseForNewPlayer.getBody().asString());
    }
}
