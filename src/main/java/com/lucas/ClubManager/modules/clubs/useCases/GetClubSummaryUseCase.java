package com.lucas.ClubManager.modules.clubs.useCases;

import com.lucas.ClubManager.modules.Exceptions.ResourceNotFoundException;
import com.lucas.ClubManager.modules.clubs.dto.ClubSummaryDTO;
import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import com.lucas.ClubManager.modules.clubs.repositories.ClubRepository;
import com.lucas.ClubManager.modules.players.repoitories.PlayerRepository;
import com.lucas.ClubManager.modules.users.entities.UserEntity;
import com.lucas.ClubManager.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetClubSummaryUseCase {

    private ClubRepository clubRepository;
    private UserRepository userRepository;

    @Autowired
    public GetClubSummaryUseCase(ClubRepository clubRepository, UserRepository userRepository){
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }
    public ClubEntity execute(ClubSummaryDTO dto){
        try{
            ClubEntity club = this.clubRepository.findById(dto.getClubId()).orElseThrow(()-> new ResourceNotFoundException("Club could not be found"));
            UserEntity user = this.userRepository.findById(dto.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User could not be found"));
            
            if(club.getId() != user.getId()){
                return club;
            }

        }catch (Exception e){
            return null;
        }
        return null;
    }
}
