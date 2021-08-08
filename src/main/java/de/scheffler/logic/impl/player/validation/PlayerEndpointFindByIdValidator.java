package de.scheffler.logic.impl.player.validation;

import de.scheffler.data.api.player.LocalPlayer;
import de.scheffler.logic.impl.common.validation.ResponseEndpointValidator;
import de.scheffler.logic.api.player.LocalPlayerService;
import org.apache.http.HttpStatus;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static de.scheffler.endpoint.api.player.dto.PlayerEndpointMessages.NO_VALID_PLAYER_ID_RESPONSE_TEXT;

@RequestScoped
public class PlayerEndpointFindByIdValidator extends ResponseEndpointValidator {

    @Inject
    LocalPlayerService playerService;
    @Override
    public ResponseEndpointValidator paramsAreLogicallyValid(Object... args) {
        LocalPlayer player = playerService.findByUniqueId((UUID)args[0]);
        if(player==null){
            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(NO_VALID_PLAYER_ID_RESPONSE_TEXT, ((UUID)args[0]).toString())).build());
            return this;
        }
        return this;
    }

}
