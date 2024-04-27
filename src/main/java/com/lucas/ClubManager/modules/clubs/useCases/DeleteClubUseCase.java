package com.lucas.ClubManager.modules.clubs.useCases;

import com.lucas.ClubManager.modules.clubs.repositories.ClubRepository;
import com.lucas.ClubManager.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteClubUseCase {
    private ClubRepository clubRepository;

    @Autowired
    public DeleteClubUseCase(ClubRepository clubRepository){
        this.clubRepository = clubRepository;
    }

    public void execute(UUID clubId){
        try{
            this.clubRepository.deleteById(clubId);
        }catch (Exception e){
            System.err.println("Error listing players:" + e.getMessage());
        }
    }
}
