package de.scheffler.endpoint.api.bowlinggame.dto;

import de.scheffler.data.api.bowling.game.BowlingGameState;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameDto {
    String gameId;

    BowlingGameState gameState;

    List<GameRunDto> gameRunDtoArrayList = new ArrayList<>();
    public String getGameId() {
        return gameId;
    }
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<GameRunDto> getGameRunDtoArrayList() {
        return gameRunDtoArrayList;
    }

    public void setGameRunDtoArrayList(List<GameRunDto> gameRunDtoArrayList) {
        this.gameRunDtoArrayList = gameRunDtoArrayList;
    }

    public void setGameState(BowlingGameState gameState) {
        this.gameState=gameState;
    }

    public BowlingGameState getGameState() {
        return gameState;
    }
}
