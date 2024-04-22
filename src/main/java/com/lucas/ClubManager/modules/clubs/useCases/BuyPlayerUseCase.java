package com.lucas.ClubManager.modules.clubs.useCases;

import com.lucas.ClubManager.modules.clubs.dto.BuyPlayerDTO;
import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import com.lucas.ClubManager.modules.clubs.repositories.ClubRepository;
import com.lucas.ClubManager.modules.players.dto.PlayerIdDTO;
import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import com.lucas.ClubManager.modules.players.repoitories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BuyPlayerUseCase {
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Transactional
    public String execute(BuyPlayerDTO dto){
        try{
            Optional<ClubEntity> optionalClub = this.clubRepository.findById(dto.getClubId());
            Optional<PlayerEntity> optionalPlayer = this.playerRepository.findById(dto.getPlayerId());

            if (optionalClub.isPresent() && optionalPlayer.isPresent()) {
                ClubEntity club = optionalClub.get();
                PlayerEntity player = optionalPlayer.get();

                //set the club reference in the player entity
                player.setClubId(club.getId());
                player.setClubEntity(club);

                //check if array is initialized if not then initiallize it
                if (club.getPlayers() == null) {
                    club.setPlayers(new ArrayList<>());
                }

                club.getPlayers().add(player);
                this.clubRepository.save(club);
                return "Sucess in buying the player";
            }else{
                return "Error in buying the player";
            }

        }catch (Exception e){
            System.err.println("Error in buying player"+ e.getMessage());
            return "Error in buying player";
        }
    }
}
