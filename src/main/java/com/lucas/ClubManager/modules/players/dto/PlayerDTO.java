package com.lucas.ClubManager.modules.players.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private String playerName;
    private int value;
    private String valueOperation; // "INCREASE" or "DECREASE"
    //private int ofensiveSkill;
    //private int keeperSkill;
    //private int defenderSkill;
}
