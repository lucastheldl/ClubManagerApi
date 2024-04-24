package com.lucas.ClubManager.modules.players.useCases;

import com.lucas.ClubManager.modules.players.dto.PlayerDTO;
import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import com.lucas.ClubManager.modules.players.repoitories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CreatePlayerUseCase {

    private PlayerRepository playerRepository;
    @Autowired
    public CreatePlayerUseCase(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public boolean execute(PlayerDTO dto){
        try{
            Random random = new Random();
            //int generatedPlayerValue = dto.getOfensiveSkill + dto.getKeeperSkill + dto.getDefenderSkill * (800 + random.nextInt(201))  ;

            var player = new PlayerEntity();
            player.setName(dto.getPlayerName());
            player.setValue(2500);

            this.playerRepository.save(player);
            return true;
        }catch (Exception e){
            System.err.println("Error creating club: " + e.getMessage());

            return false;
        }
    }
}
