package de.scheffler.logic.impl.player;

import de.scheffler.data.api.player.LocalPlayer;
import de.scheffler.data.api.player.LocalPlayerRepository;
import de.scheffler.logic.api.player.LocalPlayerService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.UUID;

@ApplicationScoped
public class PlayerServiceImpl implements LocalPlayerService {

    @Inject
    LocalPlayerRepository playerRepository;

    @Override
    public LocalPlayer findByUniqueId(UUID playerId) {
        return playerRepository.findById(playerId);
    }

    @Override
    @Transactional
    public UUID persistNewLocalPlayer(LocalPlayer newPlayer) {
        playerRepository.persist(newPlayer);
        return newPlayer.getId();
    }
}
