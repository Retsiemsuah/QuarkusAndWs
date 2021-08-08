package de.scheffler.endpoint.impl.player.requestfactory;

import de.scheffler.endpoint.api.player.dto.PlayerDto;

public class PlayerDtoBuilder {
    PlayerDto newDto;

    public PlayerDtoBuilder newPlayerDto() {
        newDto = new PlayerDto();
        return this;
    }

    public PlayerDtoBuilder withDisplayName(String name) {
        newDto.setName(name);
        return this;
    }

    public PlayerDto build(){
        return newDto;
    }
}
