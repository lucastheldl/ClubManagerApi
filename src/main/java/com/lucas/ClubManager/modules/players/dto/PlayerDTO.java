package com.lucas.ClubManager.modules.players.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private UUID playerId;
    private String playerName;
    private int value;
    //private int ofensiveSkill;
    //private int keeperSkill;
    //private int defenderSkill;
}
