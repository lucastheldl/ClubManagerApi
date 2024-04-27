package com.lucas.ClubManager.modules.clubs.useCases;


import com.lucas.ClubManager.modules.Exceptions.ResourceNotFoundException;
import com.lucas.ClubManager.modules.clubs.dto.CreateClubDTO;
import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import com.lucas.ClubManager.modules.clubs.repositories.ClubRepository;
import com.lucas.ClubManager.modules.players.repoitories.PlayerRepository;
import com.lucas.ClubManager.modules.users.entities.UserEntity;
import com.lucas.ClubManager.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateClubUseCase {

    private ClubRepository clubRepository;

    private UserRepository userRepository;

    @Autowired
    public CreateClubUseCase(ClubRepository clubRepository,UserRepository userRepository){
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;

    }

    public String execute(CreateClubDTO dto){

        try {
            Optional<ClubEntity> existingClub = clubRepository.findByName(dto.getClubName());
            UserEntity user = userRepository.findById(dto.getUserId()).orElseThrow(()-> new ResourceNotFoundException("Could not find user"));

            if (existingClub.isPresent()) {
                // No club with the same name exists, save the new club
               return "Club with name already exists";
            }

            var club = new ClubEntity();
            club.setUserId(dto.getUserId());
            club.setTotalMoney(50000);
            club.setName(dto.getClubName());
            club.setUserEntity(user);

            clubRepository.save(club);

            System.out.println("Club created: " + club.getId());
            return "Club created successfully";

        } catch (Exception e) {
            System.err.println("Error creating club: " + e.getMessage());

            return "Error creating club: " + e.getMessage();
        }
    }
}
