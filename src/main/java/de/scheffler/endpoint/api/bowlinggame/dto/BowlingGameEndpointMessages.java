package de.scheffler.endpoint.api.bowlinggame.dto;

public class BowlingGameEndpointMessages {
    public static final String NO_VALID_GAME_ID_RESPONSE_TEXT = "No game with given id %s found";
    public static final String NO_VALID_PLAYER_ID_RESPONSE= "No player with id %s found";
    public static final String EMPTY_GAME_ID_RESPONSE_TEXT = "Unable to handle request. no game id given.";
    public static final String PLAYER_ADDED_TO_GAME_RESPONSE = "Player %s added to game %s";
    public static final String PLAYER_ALREADY_ADDED_TO_THE_GAME = "Player %s already added to the game %s";
    public static final String MAX_PLAYER_LIMIT_ALREADY_REACHED = "Game %s already contains max number of players";
    public static final String PLAYER_CANT_BE_ADDED_GAME_ALREADY_STARTED= "Player %s can't be added to the game. Game already started oder finished";
    public static final String GAME_ALREADY_STARTED_CANT_BE_STARTED_AGAIN= "Game with id %s can't be started. Game already started oder finished";
    public static final String ADDING_PLAYER_FAILED_MISSING_PARAMS = "unable to add player. no game-id/playername given?";

    private BowlingGameEndpointMessages() {
    }
}