package com.lucas.ClubManager.modules.clubs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyPlayerDTO {
    private UUID clubId;
    private UUID playerId;
}
