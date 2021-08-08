package de.scheffler.endpoint.impl.bowlinggame;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.data.api.bowling.game.GameRunRepository;
import de.scheffler.endpoint.api.bowlinggame.dto.BowlingGameDto;
import de.scheffler.endpoint.api.bowlinggame.dto.GameRunDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BowlingGameDtoMapper {

    @Inject
    GameRunRepository gameRunRepository;

    @Transactional
    public BowlingGameDto createDtoFrom(BowlingGame game) {
        BowlingGameDto gameDto = new BowlingGameDto();
        gameDto.setGameId(game.getId().toString());
        gameDto.setGameState(game.getGameState());
        gameDto.setGameRunDtoArrayList(mapGameRunDtoFrom(game));
        return gameDto;
    }

    private List<GameRunDto> mapGameRunDtoFrom(BowlingGame game) {
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
        return gameRunDto;
    }
}