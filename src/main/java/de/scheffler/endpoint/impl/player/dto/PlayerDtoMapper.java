package de.scheffler.endpoint.impl.player.dto;

import de.scheffler.data.api.player.LocalPlayer;
import de.scheffler.endpoint.api.player.dto.PlayerDto;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerDtoMapper {

    public LocalPlayer from (PlayerDto incomingDto){
        LocalPlayer result = new LocalPlayer();
        result.setDisplayName(incomingDto.getName());
        return result;
    }

    public PlayerDto createDtoFrom(LocalPlayer player) {
        PlayerDto dto = new PlayerDto();
        dto.setName(player.getDisplayName());
        return dto;
    }
}
