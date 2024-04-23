package com.lucas.ClubManager.modules.clubs.controllers;


import com.lucas.ClubManager.modules.clubs.dto.BuyPlayerDTO;
import com.lucas.ClubManager.modules.clubs.dto.ClubSummaryDTO;
import com.lucas.ClubManager.modules.clubs.useCases.*;
import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private CreateClubUseCase createClubUseCase;
    @Autowired
    private BuyPlayerUseCase buyPlayerUseCase;
    @Autowired
    private SellPlayerUseCase sellPlayerUseCase;
    @Autowired
    private ListAllClubsUseCase listAllClubsUseCase;
    @Autowired
    private GetClubSummaryUseCase getClubSummaryUseCase;


    @PostMapping("/createClub")
    public ResponseEntity<String> createClub(@RequestBody ClubEntity club){

            if (club == null) {
                return ResponseEntity.badRequest().body("Club entity cannot be null");
            }
            String result = this.createClubUseCase.execute(club);
            return ResponseEntity.ok(result);
    }
    @PatchMapping("/buyPlayer")
    public ResponseEntity<String> buyPlayer(@RequestBody BuyPlayerDTO buyPlayerDTO){

            if (buyPlayerDTO.getPlayerId() == null) {
                return ResponseEntity.badRequest().body("Player invalid");
            }
            String result = this.buyPlayerUseCase.execute(buyPlayerDTO);
            return ResponseEntity.ok(result);
    }
    @PatchMapping("/sellPlayer")
    public ResponseEntity<String> sellPlayer(@RequestBody BuyPlayerDTO dto){

            if (dto.getPlayerId() == null) {
                return ResponseEntity.badRequest().body("Player invalid");
            }
            String result = this.sellPlayerUseCase.execute(dto);

            return ResponseEntity.ok(result);
    }
    @GetMapping("/paySalaries")
    public ResponseEntity<String> paySalaries(){

        //String result = this.createClubUseCase.execute(club);
        //return ResponseEntity.ok(result);
        return ResponseEntity.ok("result");
    }
    @PostMapping("/clubSummary")
    public ResponseEntity<String> clubSummary(ClubSummaryDTO dto){

        ClubEntity result = this.getClubSummaryUseCase.execute(dto);
        //return ResponseEntity.ok(result);
        return ResponseEntity.ok("result");
    }@GetMapping("/listAll")
    public ResponseEntity<List<ClubEntity>> listAllClubs(){

        var result = this.listAllClubsUseCase.execute();
        //return ResponseEntity.ok(result);
        return ResponseEntity.ok(result);
    }
}
