package com.lucas.ClubManager.modules.clubs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubSummaryDTO {
    private UUID clubId;
    private UUID userId;
}
