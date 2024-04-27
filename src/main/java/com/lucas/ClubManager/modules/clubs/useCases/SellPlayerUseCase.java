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
public class SellPlayerUseCase {

    private ClubRepository clubRepository;
    private PlayerRepository playerRepository;
    @Autowired
    public SellPlayerUseCase(ClubRepository clubRepository,PlayerRepository playerRepository){
        this.clubRepository=clubRepository;
        this.playerRepository=playerRepository;
    }
    @Transactional
    public String execute(BuyPlayerDTO dto) {
        try {
            ClubEntity club = this.clubRepository.findById(dto.getClubId()).orElseThrow(()->new ResourceNotFoundException("Club could not be found"));
            PlayerEntity player = this.playerRepository.findById(dto.getPlayerId()).orElseThrow(()->new ResourceNotFoundException("Player could not be found"));

            //set the club reference in the player entity
            player.setClubId(null);
            player.setClubEntity(null);

            //check if array is initialized if not then initiallize it
            if (club.getPlayers() == null) {
                return "Error:Club doesnt own this player";
            }
            if (!club.getPlayers().contains(player)) {
                return "Error: Club doesnt own this player";
            }
            club.getPlayers().remove(player);
            club.setTotalMoney(club.getTotalMoney() + player.getValue());
            this.clubRepository.save(club);
            return "Success in selling the player";


        } catch (Exception e) {
            System.err.println("Error in selling player" + e.getMessage());
            return "Error in selling player";
        }
    }
}
