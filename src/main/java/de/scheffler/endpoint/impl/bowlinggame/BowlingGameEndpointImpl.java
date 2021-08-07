package de.scheffler.endpoint.impl.bowlinggame;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.data.api.player.Player;
import de.scheffler.endpoint.api.bowlinggame.BowlingGameEndpoint;
import de.scheffler.endpoint.api.bowlinggame.dto.BowlingGameDto;
import de.scheffler.endpoint.api.bowlinggame.dto.GameRunDto;
import de.scheffler.logic.api.bowling.game.BowlingGameService;
import de.scheffler.logic.api.player.PlayerService;
import org.apache.http.HttpStatus;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import java.util.UUID;

public class BowlingGameEndpointImpl implements BowlingGameEndpoint {

    @Inject
    BowlingGameService bowlingGameService;

    @Inject
    PlayerService playerService;

    @Override
    public Response createNewGame() {
        UUID bowlingId = bowlingGameService.createNewGame();
        if(bowlingId==null){
            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(bowlingId).build();
    }

    @Override
    @Transactional
    public Response newPlayer(UUID uniqueGameId, UUID playerId){
//        if(uniqueGameId==null||playerId==null)
//            return Response.status(HttpStatus.SC_BAD_REQUEST).entity(ADDING_PLAYER_FAILED_MISSING_PARAMS).build();
//        BowlingGame gameFromDb = bowlingGameService.findByUniqueGameId(gameId);
//        if(gameFromDb==null)
//            return Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(NO_VALID_GAME_ID_RESPONSE_TEXT, uniqueGameId)).build();
        return Response.ok().build();
        //put into validator
//        if(gameFromDb.getRegisteredGameRuns().size()>=6)
//            return Response.status(HttpStatus.SC_BAD_REQUEST).entity( String.format(MAX_PLAYER_LIMIT_ALREADY_REACHED, uniqueGameId)).build();
//        if(gameFromDb.getRegisteredGameRuns().contains(playerId))
//            return Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(PLAYER_ALREADY_ADDED_TO_THE_GAME, playerId, uniqueGameId)).build();
//        bowlingGameService.addNewPlayerToGame(gameFromDb,playerId);
//        return Response.ok(String.format(PLAYER_ADDED_TO_GAME_RESPONSE,playerId,playerId)).build();
    }

    @Override
    @Transactional
    public BowlingGameDto findById(UUID gameId) {
        if(gameId==null)
            return null;
        BowlingGame game = bowlingGameService.findByUniqueGameId(gameId);
        BowlingGameDto gameDto = new BowlingGameDto();
        gameDto.setGameId(game.getId().toString());

        for(GameRun runFromDb : game.getRegisteredGameRuns()){
            GameRunDto runDto = new GameRunDto();
            runDto.setGamePosition(7);
            gameDto.getGameRunDtoArrayList().add(runDto);
        }
        return gameDto;
    }

    @Override
    public Player getCurrentPlayerBy(UUID uniqueGameId) {
        return null;
    }

    @Override
    public Response startGame(UUID gameId) {
        return null;
    }
}
