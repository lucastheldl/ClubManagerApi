package com.lucas.ClubManager.modules.clubs.useCases;

import com.lucas.ClubManager.modules.Exceptions.ResourceNotFoundException;
import com.lucas.ClubManager.modules.clubs.dto.BuyPlayerDTO;
import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import com.lucas.ClubManager.modules.clubs.repositories.ClubRepository;
import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import com.lucas.ClubManager.modules.players.repoitories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BuyPlayerUseCase {

    private ClubRepository clubRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public BuyPlayerUseCase(ClubRepository clubRepository,PlayerRepository playerRepository){
        this.clubRepository = clubRepository;
        this.playerRepository = playerRepository;
    }
    @Transactional
    public String execute(BuyPlayerDTO dto){
        try{
            ClubEntity club = this.clubRepository.findById(dto.getClubId()).orElseThrow(()-> new ResourceNotFoundException("Could not find Club"));
            PlayerEntity player = this.playerRepository.findById(dto.getPlayerId()).orElseThrow(()-> new ResourceNotFoundException("Could not find Player"));

            //set the club reference in the player entity
            player.setClubId(club.getId());
            player.setClubEntity(club);

            //check if array is initialized if not then initiallize it
            if (club.getPlayers() == null) {
                club.setPlayers(new ArrayList<>());
            }
            if(club.getPlayers().contains(player)){
                return "Error: Club already own this player";
            }
            /*if(club.getPlayers().size() < 22){
                return "Error: Cant sell any more players: min = 21 players";
            }*/
            club.getPlayers().add(player);
            club.setTotalMoney(club.getTotalMoney() - player.getValue());
            this.clubRepository.save(club);
            return "Success in buying the player";

        }catch (Exception e){
            System.err.println("Error in buying player"+ e.getMessage());
            return "Error in buying player";
        }
    }
}
