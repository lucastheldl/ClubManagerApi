package com.lucas.ClubManager.modules.clubs.useCases;

import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import com.lucas.ClubManager.modules.clubs.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllClubsUseCase {

    @Autowired
    private ClubRepository clubRepository;
    public List<ClubEntity> execute(){
        try{
            return this.clubRepository.findAll();

        }catch (Exception e){
            System.err.println("Error listing clubs: " + e.getMessage());

            return null;
        }
    }
}
