package com.lucas.ClubManager.modules.clubs.useCases;


import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import com.lucas.ClubManager.modules.users.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateClubUseCase {

    @Autowired
    private ClubRepository clubRepository;

    public String execute(ClubEntity club){

        try {
            clubRepository.save(club);

            System.out.println("Club created: " + club.getId());

            return "Club created successfully";
        } catch (Exception e) {
            System.err.println("Error creating club: " + e.getMessage());

            return "Error creating club: " + e.getMessage();
        }
    }
}
