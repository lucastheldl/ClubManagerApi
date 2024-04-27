package com.lucas.ClubManager.modules.players.useCases;

import com.lucas.ClubManager.modules.players.dto.PlayerDTO;
import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import com.lucas.ClubManager.modules.players.repoitories.PlayerRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdatePlayerUseCase {
    private PlayerRepository playerRepository;

    public UpdatePlayerUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    public ResponseEntity<PlayerDTO> execute(PlayerDTO dto, UUID playerId){
        try {
            Optional<PlayerEntity> optionalPlayer = this.playerRepository.findById(playerId);
            if(optionalPlayer.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            PlayerEntity player = optionalPlayer.get();

            if(!dto.getPlayerName().isEmpty()){
                player.setName(dto.getPlayerName());
            }
            if(dto.getValue() != 0 && dto.getValueOperation() != null){
                if(dto.getValueOperation().equals("DECREASE")){
                    player.setValue(player.getValue() - dto.getValue());
                };
                if(dto.getValueOperation().equals("INCREASE")){
                    player.setValue(player.getValue() + dto.getValue());
                };
            }

            this.playerRepository.save(player);

            PlayerDTO playerDto = new PlayerDTO();
            playerDto.setPlayerName(player.getName());
            playerDto.setValue(player.getValue());

            return ResponseEntity.ok().body(playerDto);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
