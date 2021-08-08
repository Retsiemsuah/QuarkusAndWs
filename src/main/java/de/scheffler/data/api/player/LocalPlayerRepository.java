package de.scheffler.data.api.player;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.UUID;

public interface LocalPlayerRepository extends PanacheRepository<LocalPlayer> {
    LocalPlayer findById(UUID idAsString);
}
