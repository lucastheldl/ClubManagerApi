package com.lucas.ClubManager.modules.players.repoitories;

import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, UUID> {
    @Query("select p from player p")
    List<PlayerEntity> getAllPlayersList();
    @Query("select p from player p WHERE p.clubId = null")
    List<PlayerEntity> getAllPlayerWithoutClubsList();
}
