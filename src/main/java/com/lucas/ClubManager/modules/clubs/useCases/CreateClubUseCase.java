package com.lucas.ClubManager.modules.clubs.useCases;

import com.lucas.ClubManager.modules.clubs.dto.CreateClubDto;
import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import com.lucas.ClubManager.modules.users.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateClubUseCase {

    @Autowired
    private ClubRepository clubRepository;

    public String execute(ClubEntity club){

        this.clubRepository.save(club);
        return "Success";
    }
}
