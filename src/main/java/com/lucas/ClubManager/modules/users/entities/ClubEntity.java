package com.lucas.ClubManager.modules.users.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubEntity {
    private UUID id;
    private String name;
    private int totalMoney;
    private List<PlayerEntity> players;

    private UUID userId;

}
