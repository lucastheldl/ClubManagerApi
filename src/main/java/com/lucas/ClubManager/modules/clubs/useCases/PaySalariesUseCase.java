package com.lucas.ClubManager.modules.clubs.useCases;

import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import com.lucas.ClubManager.modules.clubs.repositories.ClubRepository;
import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import com.lucas.ClubManager.modules.players.repoitories.PlayerRepository;
import com.lucas.ClubManager.modules.users.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> execute(UUID clubId){
        try{
            //Pay salaries
            Optional<ClubEntity> existingClub = clubRepository.findById(clubId);

            if (existingClub.isEmpty()) {
                // No club with the same name exists, save the new club
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Club not found");
            }

            var club = existingClub.get();
            var amountToPay = 0;
            for (int i = 0; i < club.getPlayers().size(); i++) {
                PlayerEntity player = club.getPlayers().get(i);
                amountToPay += player.getValue();

            }
            club.setTotalMoney(club.getTotalMoney() - amountToPay);

            clubRepository.save(club);
            return ResponseEntity.ok().body("Sucess! Salaries paid");
        }catch (Exception e){
            System.err.println("Error in paying salaries"+ e.getMessage());
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }
}
