package com.lucas.ClubManager.modules.users.repositories;

import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<ClubEntity, String> {

    @Query("SELECT c FROM club c INNER JOIN c.players ply WHERE ply.name = :playerName AND c.userEntity.email = :email")
    List<ClubEntity> findByUserEmailAndPlayerId(String email, String playerName);


}
