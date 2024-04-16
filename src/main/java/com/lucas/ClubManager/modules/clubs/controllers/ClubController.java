package com.lucas.ClubManager.modules.clubs.controllers;


import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import com.lucas.ClubManager.modules.clubs.useCases.CreateClubUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private CreateClubUseCase createClubUseCase;


    @PostMapping("/createClub")
    public String createClub(@RequestBody ClubEntity club){
        var result = this.createClubUseCase.execute(club);

        return result;
    }
}
