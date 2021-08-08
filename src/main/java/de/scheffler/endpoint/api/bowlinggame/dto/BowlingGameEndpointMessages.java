package de.scheffler.endpoint.api.bowlinggame.dto;

public class BowlingGameEndpointMessages {
    public static final String SUCCESS_PLAYER_ADDED_TO_GAME = "Player %s added to game %s";

    public static final String ERROR_NO_VALID_GAME_ID = "No game with given id %s found";
    public static final String ERROR_NO_VALID_PLAYER_ID = "No player with id %s found";
    public static final String ERROR_MAX_PLAYER_LIMIT_ALREADY_REACHED = "Game %s already contains max number of players";
    public static final String ERROR_PLAYER_CANT_BE_ADDED_GAME_ALREADY_STARTED = "Player %s can't be added to the game. Game already started oder finished";
    public static final String ERROR_GAME_CANT_BE_STARTED_AGAIN = "Game with id %s can't be started. Game already started oder finished";
    public static final String ERROR_THROW_CANT_BE_ADDED_GAME_NOT_RUNNING = "Current throw can't be added. Game with id %s does not run";
    public static final String ERROR_THROW_HAS_INVALID_VALUE = "Current throw can't be added. Throw value can't be lower than 0 or higher than 9.";

    private BowlingGameEndpointMessages() {
    }
}