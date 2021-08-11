package de.scheffler.endpoint.impl.bowlinggame.dto;

import de.scheffler.data.api.bowling.game.Frame;
import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.endpoint.api.bowlinggame.dto.FrameDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FrameDtoMapper {

    public List<FrameDto> mapFrameDtosFrom(GameRun run) {
        List<FrameDto> frameDtos = new ArrayList<>();
        for(Frame frameToConvert : run.getFrameHistory()){
            FrameDto dto = new FrameDto();
            dto.setFrameNumber(frameToConvert.getFramePosition());
            dto.setFirstThrow(frameToConvert.getFirstThrow());
            dto.setSecondThrow(frameToConvert.getSecondThrow());
            dto.setThirdThrow(frameToConvert.getThirdThrow());
            dto.setScore(frameToConvert.getTotalScore());
            frameDtos.add(dto);
        }
        return frameDtos;
    }
}
