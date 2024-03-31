package com.lucas.ClubManager.modules.users.repositories;

import com.lucas.ClubManager.modules.users.entities.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClubRepository extends JpaRepository<ClubEntity, String> {

    @Query("SELECT c FROM club c INNER JOIN c.players ply WHERE ply.name = :playerName AND c.userEntity.email = :email")
    List<ClubEntity> findByUserEmailAndPlayerId(String email, String playerName);
}
