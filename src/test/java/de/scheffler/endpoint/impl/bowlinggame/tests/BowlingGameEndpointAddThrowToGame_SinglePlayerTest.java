package de.scheffler.endpoint.impl.bowlinggame.tests;

import de.scheffler.data.api.bowling.game.BowlingGameState;
import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.endpoint.api.bowlinggame.dto.BowlingGameDto;
import de.scheffler.endpoint.api.bowlinggame.dto.FrameDto;
import de.scheffler.endpoint.api.bowlinggame.dto.GameRunDto;
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

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class BowlingGameEndpointAddThrowToGame_SinglePlayerTest {

    @Inject
    BowlingGameEndpointRequestResponseFactory bowlingRequestFactory;

    @Inject
    PlayerEndpointRequestResponseFactory playerRequestFactory;

    UUID gameId;

    @BeforeEach
    void setUpGame() {
        gameId = bowlingRequestFactory.getNewBowlingGameResponse().body().as(UUID.class);
        UUID playerId = playerRequestFactory.createNewPlayerResponse(new PlayerDtoBuilder().newPlayerDto().withDisplayName("TESTPLAYER").build()).body().as(UUID.class);
        bowlingRequestFactory.addNewPlayerToGame(gameId, playerId);
        bowlingRequestFactory.startBowlingGameResponse(gameId);
    }

    @Test
    void addSingleThrowToGame() {
        Response responseOfThrow = bowlingRequestFactory.addThrowToGame(1, gameId);
        assertEquals(HttpStatus.SC_OK, responseOfThrow.statusCode());
        BowlingGameDto gameAfterThrow = bowlingRequestFactory.findBowlingGameByIdResponse(gameId).body().as(BowlingGameDto.class);
        assertEquals(BowlingGameState.RUNNING,gameAfterThrow.getGameState());

        GameRunDto runDto = gameAfterThrow.getGameRunDtoArrayList().get(0);
        FrameDto frame = findSpecificFrameFromGameRun(runDto, 0);
        assertFrameHasValues(frame, 1, null, null, null);

        for (int i = 1; i < runDto.getFrames().size(); i++) {
            assertFrameValuesAreAllNull(findSpecificFrameFromGameRun(runDto, i));
        }
    }

    @Test
    void addSingleStrikeToGame() {
        Response responseOfThrow = bowlingRequestFactory.addThrowToGame(10, gameId);
        assertEquals(HttpStatus.SC_OK, responseOfThrow.statusCode());
        BowlingGameDto gameAfterThrow = bowlingRequestFactory.findBowlingGameByIdResponse(gameId).body().as(BowlingGameDto.class);
        assertEquals(BowlingGameState.RUNNING,gameAfterThrow.getGameState());

        GameRunDto runDto = gameAfterThrow.getGameRunDtoArrayList().get(0);
        FrameDto frame = findSpecificFrameFromGameRun(runDto, 0);
        assertFrameHasValues(frame, 10, null, null, null);

        for (int i = 1; i < runDto.getFrames().size(); i++) {
            assertFrameValuesAreAllNull(findSpecificFrameFromGameRun(runDto, i));
        }
    }

    @Test
    void addSingleSpareToGame() {
        bowlingRequestFactory.addThrowToGame(5, gameId);
        Response responseOfSpareThrow = bowlingRequestFactory.addThrowToGame(5, gameId);
        assertEquals(HttpStatus.SC_OK, responseOfSpareThrow.statusCode());

        BowlingGameDto gameAfterThrow = bowlingRequestFactory.findBowlingGameByIdResponse(gameId).body().as(BowlingGameDto.class);
        assertEquals(BowlingGameState.RUNNING,gameAfterThrow.getGameState());

        GameRunDto runDto = gameAfterThrow.getGameRunDtoArrayList().get(0);
        FrameDto frame = findSpecificFrameFromGameRun(runDto, 0);
        assertFrameHasValues(frame, 5, 5, null, null);

        for (int i = 1; i < runDto.getFrames().size(); i++) {
            assertFrameValuesAreAllNull(findSpecificFrameFromGameRun(runDto, i));
        }
    }

    @Test
    void addOneThrowAfterStrikeToGame() {
        bowlingRequestFactory.addThrowToGame(10, gameId);
        Response responseOfThrow = bowlingRequestFactory.addThrowToGame(5, gameId);
        assertEquals(HttpStatus.SC_OK, responseOfThrow.statusCode());
        BowlingGameDto gameAfterThrow = bowlingRequestFactory.findBowlingGameByIdResponse(gameId).body().as(BowlingGameDto.class);
        assertEquals(BowlingGameState.RUNNING,gameAfterThrow.getGameState());

        GameRunDto runDto = gameAfterThrow.getGameRunDtoArrayList().get(0);
        FrameDto firstFrame = findSpecificFrameFromGameRun(runDto, 0);
        assertFrameHasValues(firstFrame, 10, null, null,null);

        FrameDto secondFrame = findSpecificFrameFromGameRun(runDto, 1);
        assertFrameHasValues(secondFrame, 5, null, null, null);

        for (int i = 2; i < runDto.getFrames().size(); i++) {
            assertFrameValuesAreAllNull(findSpecificFrameFromGameRun(runDto, i));
        }
    }

    @Test
    void addTwoThrowsAfterStrikeToGame() {
        bowlingRequestFactory.addThrowToGame(10, gameId);
        bowlingRequestFactory.addThrowToGame(4, gameId);
        Response responseOfThrow = bowlingRequestFactory.addThrowToGame(4, gameId);
        assertEquals(HttpStatus.SC_OK, responseOfThrow.statusCode());

        BowlingGameDto gameAfterThrow = bowlingRequestFactory.findBowlingGameByIdResponse(gameId).body().as(BowlingGameDto.class);
        assertEquals(BowlingGameState.RUNNING,gameAfterThrow.getGameState());

        GameRunDto runDto = gameAfterThrow.getGameRunDtoArrayList().get(0);
        FrameDto firstFrame = findSpecificFrameFromGameRun(runDto, 0);
        assertFrameHasValues(firstFrame, 10, null, null,18);

        FrameDto secondFrame = findSpecificFrameFromGameRun(runDto, 1);
        assertFrameHasValues(secondFrame, 4, 4, null, 26);

        for (int i = 2; i < runDto.getFrames().size(); i++) {
            assertFrameValuesAreAllNull(findSpecificFrameFromGameRun(runDto, i));
        }
    }

    @Test
    void addTwoThrowsToGameNoSpare() {
        bowlingRequestFactory.addThrowToGame(1, gameId);
        bowlingRequestFactory.addThrowToGame(5, gameId);
        BowlingGameDto gameAfterThrow = bowlingRequestFactory.findBowlingGameByIdResponse(gameId).body().as(BowlingGameDto.class);
        assertEquals(BowlingGameState.RUNNING,gameAfterThrow.getGameState());

        GameRunDto runDto = gameAfterThrow.getGameRunDtoArrayList().get(0);
        FrameDto frame = findSpecificFrameFromGameRun(runDto, 0);
        assertFrameHasValues(frame, 1, 5, null, 6);

        for (int i = 1; i < runDto.getFrames().size(); i++) {
            assertFrameValuesAreAllNull(findSpecificFrameFromGameRun(runDto, i));
        }
    }



    private void assertFrameHasValues(FrameDto frameFromRespones, Integer firstThrow, Integer secondThrow, Integer thirdThrow, Integer score) {
        assertEquals(firstThrow, frameFromRespones.getFirstThrow());
        assertEquals(secondThrow, frameFromRespones.getSecondThrow());
        assertEquals(thirdThrow, frameFromRespones.getThirdThrow());
        assertEquals(score, frameFromRespones.getScore());
    }

    @Test
    void addThreeThrowsToGameNoSpare() {
        bowlingRequestFactory.addThrowToGame(1, gameId);
        bowlingRequestFactory.addThrowToGame(2, gameId);
        bowlingRequestFactory.addThrowToGame(2, gameId);
        BowlingGameDto gameAfterThrow = bowlingRequestFactory.findBowlingGameByIdResponse(gameId).body().as(BowlingGameDto.class);
        assertEquals(BowlingGameState.RUNNING,gameAfterThrow.getGameState());

        GameRunDto runDto = gameAfterThrow.getGameRunDtoArrayList().get(0);
        FrameDto frameZero = findSpecificFrameFromGameRun(runDto, 0);
        assertFrameHasValues(frameZero, 1, 2, null, 3);

        FrameDto frameOne = findSpecificFrameFromGameRun(runDto, 1);
        assertFrameHasValues(frameOne, 2, null, null, null);

        for (int i = 2; i < runDto.getFrames().size(); i++) {
            assertFrameValuesAreAllNull(findSpecificFrameFromGameRun(runDto, i));
        }
    }

    @Test
    void addFourThrowsToGameNoSpare() {
        bowlingRequestFactory.addThrowToGame(1, gameId);
        bowlingRequestFactory.addThrowToGame(2, gameId);
        bowlingRequestFactory.addThrowToGame(2, gameId);
        bowlingRequestFactory.addThrowToGame(3, gameId);
        BowlingGameDto gameAfterThrow = bowlingRequestFactory.findBowlingGameByIdResponse(gameId).body().as(BowlingGameDto.class);

        GameRunDto runDto = gameAfterThrow.getGameRunDtoArrayList().get(0);
        FrameDto frameOne = findSpecificFrameFromGameRun(runDto, 1);
        assertFrameHasValues(frameOne, 2, 3, null, 8);

        for (int i = 2; i < runDto.getFrames().size(); i++) {
            assertFrameValuesAreAllNull(findSpecificFrameFromGameRun(runDto, i));
        }
    }

    @Test
    void addFinishGameWithOnlyOneThrowsThrowsToGameNoSpare() {
        BowlingGameDto gameBeforeThrows = bowlingRequestFactory.findBowlingGameByIdResponse(gameId).body().as(BowlingGameDto.class);
        int numberOfThrows = gameBeforeThrows.getGameRunDtoArrayList().get(0).getFrames().size()*2;
        for(int i = 0 ; i<=numberOfThrows;i++){
            bowlingRequestFactory.addThrowToGame(1, gameId);
        }

        BowlingGameDto gameAfterThrows = bowlingRequestFactory.findBowlingGameByIdResponse(gameId).body().as(BowlingGameDto.class);
        assertEquals(BowlingGameState.FINISHED, gameAfterThrows.getGameState());

        int oneIsThrown = 1;
        int summOfAllThrows=0;
        for(int i = 0 ; i<gameBeforeThrows.getGameRunDtoArrayList().get(0).getFrames().size();i++){
            summOfAllThrows=summOfAllThrows+oneIsThrown+oneIsThrown;
            assertFrameHasValues(gameAfterThrows.getGameRunDtoArrayList().get(0).getFrames().get(i), oneIsThrown, oneIsThrown, null, summOfAllThrows);
        }
    }

    private void assertFrameValuesAreAllNull(FrameDto specificFrameFromGameRun) {
        assertNull(specificFrameFromGameRun.getFirstThrow());
        assertNull(specificFrameFromGameRun.getSecondThrow());
        assertNull(specificFrameFromGameRun.getThirdThrow());
        assertNull(specificFrameFromGameRun.getScore());
    }

    private FrameDto findSpecificFrameFromGameRun(GameRunDto gameRunDto, int searchedFrameNumber) {
        for (FrameDto dto : gameRunDto.getFrames())
            if (dto.getFrameNumber() == searchedFrameNumber)
                return dto;

        return null;
    }
}
