package com.lucas.ClubManager.modules.clubs.useCases;

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
            Optional<ClubEntity> optionalClub = this.clubRepository.findById(dto.getClubId());
            Optional<PlayerEntity> optionalPlayer = this.playerRepository.findById(dto.getPlayerId());

            if (optionalClub.isPresent() && optionalPlayer.isPresent()) {

                ClubEntity club = optionalClub.get();
                PlayerEntity player = optionalPlayer.get();


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
            }
            return "Error club or player not found";
        } catch (Exception e) {
            System.err.println("Error in selling player" + e.getMessage());
            return "Error in selling player";
        }
    }
}
