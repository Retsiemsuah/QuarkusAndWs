package de.scheffler.logic.api.player;

import de.scheffler.data.api.player.Player;

public interface PlayerService {
    Player loadOrCreateBy(Long playerId);
}
