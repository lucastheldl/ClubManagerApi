package com.lucas.ClubManager.modules.players.useCases;

import com.lucas.ClubManager.modules.players.dto.PlayerDTO;
import com.lucas.ClubManager.modules.players.dto.PlayerResponse;
import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import com.lucas.ClubManager.modules.players.repoitories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PlayerResponse execute(int pageNo, int pageSize){
        try {
            Pageable pageable = PageRequest.of(pageNo,pageSize);
            Page< PlayerEntity > players = this.playerRepository.findAll(pageable);
            List<PlayerEntity> playersList = players.getContent();

            //Stream is used to map a list of objects to another format using an mapTo custom function
            //map instead a for loop is because map return a new list
            List<PlayerDTO> content = playersList.stream().map(p -> mapToDTO(p)).collect(Collectors.toList());

            PlayerResponse playersResponse = new PlayerResponse();
            playersResponse.setContent(content);
            playersResponse.setPageNo(players.getNumber());
            playersResponse.setPageSize(players.getSize());
            playersResponse.setTotalElements(players.getTotalElements());
            playersResponse.setTotalPages(players.getTotalPages());
            playersResponse.setLast(players.isLast());

            return playersResponse;

        }catch (Exception e){
            System.err.println("Error listing players:" + e.getMessage());
            return null;
        }
    }

    private PlayerDTO mapToDTO(PlayerEntity playerEntity){
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerName(playerEntity.getName());
        playerDTO.setValue(playerEntity.getValue());

        return playerDTO;

    }
}
