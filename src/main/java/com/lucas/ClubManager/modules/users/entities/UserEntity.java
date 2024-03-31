package com.lucas.ClubManager.modules.users.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="userClient")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true,nullable = false, length = 100)
    private String username;

    //@Column(name="email_usuario")
    @Column(unique = true,nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name="club_id")
    private UUID clubId;

    @OneToOne
    @JoinColumn(name="club_id",insertable = false,updatable = false)
    private ClubEntity clubEntity;
}
