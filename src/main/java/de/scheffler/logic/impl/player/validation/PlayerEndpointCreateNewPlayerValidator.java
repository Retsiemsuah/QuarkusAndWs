package de.scheffler.logic.impl.player.validation;

import de.scheffler.endpoint.api.player.dto.PlayerDto;
import de.scheffler.logic.impl.common.validation.ResponseEndpointValidator;
import org.apache.http.HttpStatus;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;

import static de.scheffler.endpoint.api.player.dto.PlayerEndpointMessages.REQUIRED_FIELDS_MISSING;

@RequestScoped
public class PlayerEndpointCreateNewPlayerValidator extends ResponseEndpointValidator {

    @Override
    public ResponseEndpointValidator paramsAreLogicallyValid(Object... args) {
        PlayerDto player = (PlayerDto) args[0];
        if(player.getName().isBlank()){
            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(REQUIRED_FIELDS_MISSING)).build());
            return this;
        }
        return this;
    }

}
