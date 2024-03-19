package com.lucas.ClubManager.modules.users.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private ClubEntity club;
}
