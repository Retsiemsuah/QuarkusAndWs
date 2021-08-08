package de.scheffler.endpoint.api.player.dto;

public class PlayerEndpointMessages {
    public static final String REQUIRED_FIELDS_MISSING = "Unable to add player. Required fields missing";
    public static final String NO_VALID_PLAYER_ID_RESPONSE_TEXT = "No player with given id %s found";
    public static final String EMPTY_GAME_ID_RESPONSE_TEXT = "Unable to handle request. no player id given.";
    private PlayerEndpointMessages() {
    }
}