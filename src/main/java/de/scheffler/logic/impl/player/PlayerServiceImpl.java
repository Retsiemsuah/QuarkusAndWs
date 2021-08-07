package de.scheffler.logic.impl.player;

import de.scheffler.data.api.player.Player;
import de.scheffler.data.api.player.PlayerRepository;
import de.scheffler.logic.api.player.PlayerService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PlayerServiceImpl implements PlayerService {

    @Inject
    PlayerRepository playerRepository;

    @Override
    public Player loadOrCreateBy(Long playerId) {
        Player playerFromDb = playerRepository.findById(playerId);
            if(playerFromDb==null)
                playerFromDb= new Player();
        return playerFromDb;
    }
}
