package com.lucas.ClubManager.modules.clubs.controllers;


import com.lucas.ClubManager.modules.players.dto.PlayerIdDTO;
import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import com.lucas.ClubManager.modules.clubs.useCases.CreateClubUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private CreateClubUseCase createClubUseCase;


    @PostMapping("/createClub")
    public ResponseEntity<String> createClub(@RequestBody ClubEntity club){

            if (club == null) {
                return ResponseEntity.badRequest().body("Club entity cannot be null");
            }
            String result = this.createClubUseCase.execute(club);
            return ResponseEntity.ok(result);
    }
    @PostMapping("/buyPlayer")
    public ResponseEntity<String> buyPlayer(@RequestBody PlayerIdDTO playerIdDto){

            if (playerIdDto.getPlayerId() == null) {
                return ResponseEntity.badRequest().body("Player invalid");
            }
            //String result = this.createClubUseCase.execute(club);
            //return ResponseEntity.ok(result);
        return ResponseEntity.ok("result");
    }
    @PatchMapping("/sellPlayer")
    public ResponseEntity<String> sellPlayer(@RequestBody PlayerIdDTO playerIdDto){

            if (playerIdDto.getPlayerId() == null) {
                return ResponseEntity.badRequest().body("Player invalid");
            }
            //String result = this.createClubUseCase.execute(club);
            //return ResponseEntity.ok(result);
        return ResponseEntity.ok("result");
    }
    @GetMapping("/paySalaries")
    public ResponseEntity<String> paySalaries(){

        //String result = this.createClubUseCase.execute(club);
        //return ResponseEntity.ok(result);
        return ResponseEntity.ok("result");
    }
    @GetMapping("/clubSummary")
    public ResponseEntity<String> clubSummary(){

        //String result = this.createClubUseCase.execute(club);
        //return ResponseEntity.ok(result);
        return ResponseEntity.ok("result");
    }
}
