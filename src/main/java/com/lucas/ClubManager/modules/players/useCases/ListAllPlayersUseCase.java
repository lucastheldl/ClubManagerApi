package com.lucas.ClubManager.modules.players.useCases;

import com.lucas.ClubManager.modules.players.dto.PlayerDTO;
import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import com.lucas.ClubManager.modules.players.repoitories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAllPlayersUseCase {

    private PlayerRepository playerRepository;
    @Autowired
    public ListAllPlayersUseCase(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }
    public List<PlayerDTO> execute(){
        try {
            List< PlayerEntity > playersList = this.playerRepository.getAllPlayersList();

            //Stream is used to map a list of objects to another format using an mapTo custom function
            //map instead a for loop is because map return a new list
            return playersList.stream().map(p -> mapToDTO(p)).collect(Collectors.toList());

        }catch (Exception e){
            System.err.println("Error listing players:" + e.getMessage());
            return null;
        }
    }

    private PlayerDTO mapToDTO(PlayerEntity playerEntity){
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerName(playerEntity.getName());
        playerDTO.setPlayerId(playerEntity.getId());
        playerDTO.setValue(playerEntity.getValue());

        return playerDTO;

    }
}
