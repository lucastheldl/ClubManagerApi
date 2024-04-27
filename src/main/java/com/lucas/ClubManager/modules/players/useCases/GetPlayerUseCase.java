package com.lucas.ClubManager.modules.players.useCases;

import com.lucas.ClubManager.modules.Exceptions.ResourceNotFoundException;
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
            PlayerEntity player = playerRepository.findById(playerId).orElseThrow(()->new ResourceNotFoundException("Cannot find Player"));

            PlayerDTO playerDto = new PlayerDTO();

            playerDto.setPlayerName(player.getName());
            playerDto.setValue(player.getValue());

            return ResponseEntity.ok().body(playerDto);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
