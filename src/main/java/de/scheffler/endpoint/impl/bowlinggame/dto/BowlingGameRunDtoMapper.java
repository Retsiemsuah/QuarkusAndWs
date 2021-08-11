package de.scheffler.endpoint.impl.bowlinggame.dto;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.data.api.bowling.game.GameRunRepository;
import de.scheffler.endpoint.api.bowlinggame.dto.GameRunDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BowlingGameRunDtoMapper {

    @Inject
    GameRunRepository gameRunRepository;

    @Inject
    FrameDtoMapper frameMapper;

    @Transactional
    public List<GameRunDto> mapGameRunDtoFrom(BowlingGame game) {
        List<GameRunDto>runDtos = new ArrayList<>();
        List<GameRun> runsToForGame=gameRunRepository.findBy(game);
        if(runsToForGame.isEmpty()){
            return runDtos;
        }
        for(GameRun run:runsToForGame){
            runDtos.add(getRunDtoFrom(run));
        }
        return runDtos;
    }


    private GameRunDto getRunDtoFrom(GameRun run) {
        GameRunDto gameRunDto = new GameRunDto();
        gameRunDto.setGamePosition(run.getRunNumberWithinGame());
        gameRunDto.setPlayerDisplayName(run.getPlayer().getDisplayName());
        gameRunDto.setFrames(frameMapper.mapFrameDtosFrom(run));
        return gameRunDto;
    }
}
