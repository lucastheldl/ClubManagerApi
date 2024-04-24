package com.lucas.ClubManager.modules.clubs.useCases;

import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import com.lucas.ClubManager.modules.clubs.repositories.ClubRepository;
import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import com.lucas.ClubManager.modules.players.repoitories.PlayerRepository;
import com.lucas.ClubManager.modules.users.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaySalariesUseCase {

    private ClubRepository clubRepository;

    @Autowired
    public PaySalariesUseCase(ClubRepository clubRepository){

        this.clubRepository=clubRepository;
    }
    public String execute(UUID clubId){
        try{
            //Pay salaries
            Optional<ClubEntity> existingClub = clubRepository.findById(clubId);

            if (existingClub.isEmpty()) {
                // No club with the same name exists, save the new club
                return "Club Don't exists";
            }

            var club = existingClub.get();
            var amountToPay = 0;
            for (int i = 0; i < club.getPlayers().size(); i++) {
                PlayerEntity player = club.getPlayers().get(i);
                amountToPay += player.getValue();

            }
            club.setTotalMoney(club.getTotalMoney() - amountToPay);

            clubRepository.save(club);
        }catch (Exception e){
            System.err.println("Error in paying salaries"+ e.getMessage());
        }
        return "";
    }
}
