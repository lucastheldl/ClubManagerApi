package com.lucas.ClubManager.modules.users.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity {
    private UUID id;
    private String name;
    private int value;

    private UUID clubId;
}
