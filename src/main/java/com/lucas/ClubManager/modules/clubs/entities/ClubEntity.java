package com.lucas.ClubManager.modules.clubs.entities;

import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import com.lucas.ClubManager.modules.users.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="club")
public class ClubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private int totalMoney;

    @JoinColumn(name="userClient_id")
    private UUID userId;


    @OneToOne
    //@JoinColumn(name="user_id", insertable = false,updatable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "clubEntity",cascade = CascadeType.ALL)
    private List<PlayerEntity> players;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
