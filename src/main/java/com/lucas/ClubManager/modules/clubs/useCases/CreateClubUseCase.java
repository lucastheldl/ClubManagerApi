package com.lucas.ClubManager.modules.clubs.useCases;


import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import com.lucas.ClubManager.modules.clubs.repositories.ClubRepository;
import com.lucas.ClubManager.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateClubUseCase {

    @Autowired
    private ClubRepository clubRepository;

    public String execute(ClubEntity club){

        try {
            Optional<ClubEntity> existingClub = clubRepository.findByName(club.getName());

            if (existingClub.isPresent()) {
               return "Club with name already exists";
            } else {
                // No club with the same name exists, save the new club
                clubRepository.save(club);
                System.out.println("Club created: " + club.getId());
                return "Club created successfully";
            }
        } catch (Exception e) {
            System.err.println("Error creating club: " + e.getMessage());

            return "Error creating club: " + e.getMessage();
        }
    }
}
