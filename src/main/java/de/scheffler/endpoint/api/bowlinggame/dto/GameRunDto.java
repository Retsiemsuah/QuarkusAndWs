package de.scheffler.endpoint.api.bowlinggame.dto;

import java.util.ArrayList;
import java.util.List;

public class GameRunDto {
    int gamePosition;
    String playerDisplayName;

    List<FrameDto> frames = new ArrayList<>();

    public int getGamePosition() {
        return gamePosition;
    }

    public void setGamePosition(int gamePosition) {
        this.gamePosition = gamePosition;
    }

    public List<FrameDto> getFrames() {
        return frames;
    }

    public void setFrames(List<FrameDto> frames) {
        this.frames = frames;
    }

    public String getPlayerDisplayName() {
        return playerDisplayName;
    }

    public void setPlayerDisplayName(String playerDisplayName) {
        this.playerDisplayName = playerDisplayName;
    }
}
