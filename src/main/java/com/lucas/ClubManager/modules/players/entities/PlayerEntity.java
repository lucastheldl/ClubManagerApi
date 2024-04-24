package com.lucas.ClubManager.modules.players.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="player")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private int value;

    //@Column(columnDefinition = "INTEGER DEFAULT 0")
    //private int goals;

    @Column(name="club_id")
    private UUID clubId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="club_id",insertable = false,updatable = false)
    private ClubEntity clubEntity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="titular_club_id",insertable = false,updatable = false)
    private ClubEntity titularInClubEntity;
}
