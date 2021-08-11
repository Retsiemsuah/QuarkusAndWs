package de.scheffler.endpoint.impl.bowlinggame.dto;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.data.api.bowling.game.Frame;
import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.data.api.bowling.game.GameRunRepository;
import de.scheffler.endpoint.api.bowlinggame.dto.BowlingGameDto;
import de.scheffler.endpoint.api.bowlinggame.dto.FrameDto;
import de.scheffler.endpoint.api.bowlinggame.dto.GameRunDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BowlingGameDtoMapper {

    @Inject
    BowlingGameRunDtoMapper gameRunMapper;


    public BowlingGameDto createDtoFrom(BowlingGame game) {
        BowlingGameDto gameDto = new BowlingGameDto();
        gameDto.setGameId(game.getId().toString());
        gameDto.setGameState(game.getGameState());
        gameDto.setGameRunDtoArrayList(gameRunMapper.mapGameRunDtoFrom(game));
        return gameDto;
    }




}