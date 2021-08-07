package de.scheffler.endpoint.api.bowlinggame.dto;

public class BowlingGameEndpointMessages {
    public static final String NO_VALID_GAME_ID_RESPONSE_TEXT = "No game with given %s found";
    public static final String PLAYER_ADDED_TO_GAME_RESPONSE = "Player %s added to game %s";
    public static final String PLAYER_ALREADY_ADDED_TO_THE_GAME = "Player %s already added to the game %s";
    public static final String MAX_PLAYER_LIMIT_ALREADY_REACHED = "Game %s already contains max number of players";
    public static final String ADDING_PLAYER_FAILED_MISSING_PARAMS = "unable to add player. no game-id/playername given?";

    private BowlingGameEndpointMessages() {
    }
}