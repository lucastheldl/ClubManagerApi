package com.lucas.ClubManager.modules.players.useCases;

import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import com.lucas.ClubManager.modules.players.repoitories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllPlayersUseCase {
    @Autowired
    private PlayerRepository playerRepository;
    public List<PlayerEntity> execute(){
        try {
            List< PlayerEntity > playersList = this.playerRepository.getAllPlayersList();
            return playersList;
        }catch (Exception e){
            System.err.println("Error listing players:" + e.getMessage());
            return null;
        }
    }
}
