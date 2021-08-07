package de.scheffler.endpoint.api.bowlinggame.dto;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameDto {
    String gameId;
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
}
