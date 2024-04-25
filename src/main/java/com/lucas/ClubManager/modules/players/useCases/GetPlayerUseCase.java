package com.lucas.ClubManager.modules.players.useCases;

import com.lucas.ClubManager.modules.players.dto.PlayerDTO;
import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import com.lucas.ClubManager.modules.players.repoitories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetPlayerUseCase {

    private PlayerRepository playerRepository;
    @Autowired
    public GetPlayerUseCase(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public ResponseEntity<PlayerDTO> execute(UUID playerId){
        try{
            Optional<PlayerEntity> optionalPlayer = playerRepository.findById(playerId);

            if(optionalPlayer.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            PlayerEntity player = optionalPlayer.get();
            PlayerDTO playerDto = new PlayerDTO();

            playerDto.setPlayerId(player.getId());
            playerDto.setPlayerName(player.getName());

            return ResponseEntity.ok().body(playerDto);

        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
