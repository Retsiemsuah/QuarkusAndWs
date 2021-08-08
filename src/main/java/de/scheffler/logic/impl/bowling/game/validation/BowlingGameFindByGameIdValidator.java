package de.scheffler.logic.impl.bowling.game.validation;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.logic.impl.common.validation.ResponseEndpointValidator;
import de.scheffler.logic.api.bowling.game.BowlingGameService;
import org.apache.http.HttpStatus;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.UUID;

import static de.scheffler.endpoint.api.bowlinggame.dto.BowlingGameEndpointMessages.ERROR_NO_VALID_GAME_ID;

@RequestScoped
public class BowlingGameFindByGameIdValidator extends ResponseEndpointValidator {

    @Inject
    BowlingGameService bowlingGameService;

    @Override
    public ResponseEndpointValidator paramsAreLogicallyValid(Object... args) {
        BowlingGame game = bowlingGameService.findByUniqueGameId((UUID)args[0]);
        if(game==null){
            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(ERROR_NO_VALID_GAME_ID, ((UUID)args[0]).toString())).build());
            return this;
        }
        return this;
    }
}
