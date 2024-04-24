package com.lucas.ClubManager.modules.players.useCases;

import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import com.lucas.ClubManager.modules.players.repoitories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePlayerUseCase {

    private PlayerRepository playerRepository;
    @Autowired
    public CreatePlayerUseCase(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public boolean execute(PlayerEntity player){
        try{
            this.playerRepository.save(player);
            return true;
        }catch (Exception e){
            System.err.println("Error creating club: " + e.getMessage());

            return false;
        }
    }
}
