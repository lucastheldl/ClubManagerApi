package com.lucas.ClubManager.modules.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyHasPlayerDTO {
    private String email;
    private String playerName;
}
