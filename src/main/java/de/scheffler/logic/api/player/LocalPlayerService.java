package de.scheffler.logic.api.player;

import de.scheffler.data.api.player.LocalPlayer;

import java.util.UUID;

public interface LocalPlayerService {

    LocalPlayer findByUniqueId(UUID playerId);

    UUID persistNewLocalPlayer(LocalPlayer from);
}
