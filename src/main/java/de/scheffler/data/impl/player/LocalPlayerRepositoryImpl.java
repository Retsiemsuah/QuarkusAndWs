package de.scheffler.data.impl.player;

import de.scheffler.data.api.player.LocalPlayer;
import de.scheffler.data.api.player.LocalPlayerRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class LocalPlayerRepositoryImpl implements LocalPlayerRepository {
    @Override
    public LocalPlayer findById(UUID idAsUuid) {
        return find("id", idAsUuid).firstResult();
    }
}
