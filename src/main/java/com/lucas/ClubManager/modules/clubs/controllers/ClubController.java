package com.lucas.ClubManager.modules.clubs.controllers;


import com.lucas.ClubManager.modules.clubs.dto.*;
import com.lucas.ClubManager.modules.clubs.useCases.*;
import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/club")
public class ClubController {

    private CreateClubUseCase createClubUseCase;
    private BuyPlayerUseCase buyPlayerUseCase;
    private SellPlayerUseCase sellPlayerUseCase;
    private ListAllClubsUseCase listAllClubsUseCase;
    private GetClubSummaryUseCase getClubSummaryUseCase;
    private PaySalariesUseCase paySalariesUseCase;
    private DeleteClubUseCase deleteClubUseCase;
    @Autowired
    public ClubController(CreateClubUseCase createClubUseCase, BuyPlayerUseCase buyPlayerUseCase, SellPlayerUseCase sellPlayerUseCase, ListAllClubsUseCase listAllClubsUseCase, GetClubSummaryUseCase getClubSummaryUseCase,PaySalariesUseCase paySalariesUseCase,DeleteClubUseCase deleteClubUseCase) {
        this.createClubUseCase = createClubUseCase;
        this.buyPlayerUseCase = buyPlayerUseCase;
        this.sellPlayerUseCase = sellPlayerUseCase;
        this.listAllClubsUseCase = listAllClubsUseCase;
        this.getClubSummaryUseCase = getClubSummaryUseCase;
        this.paySalariesUseCase = paySalariesUseCase;
        this.deleteClubUseCase = deleteClubUseCase;
    }

    @PostMapping("/createClub")
    public ResponseEntity<String> createClub(@RequestBody CreateClubDTO dto){

            if (dto == null) {
                return ResponseEntity.badRequest().body("Club entity cannot be null");
            }
            String result = this.createClubUseCase.execute(dto);
            return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> createClub(@PathVariable("id")UUID clubId){

            if (clubId == null) {
                return ResponseEntity.badRequest().body("Club cannot be found");
            }
             this.deleteClubUseCase.execute(clubId);
            return ResponseEntity.ok("Club deleted");
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
    @GetMapping("/paySalaries/{id}")
    public ResponseEntity<String> paySalaries(@PathVariable("id") UUID clubId){

        ResponseEntity<String> result = this.paySalariesUseCase.execute(clubId);
        return ResponseEntity.ok(result.getBody());
    }
    @PostMapping("/clubSummary")
    public ResponseEntity<ClubEntity> clubSummary(@RequestBody ClubSummaryDTO dto){

        if(dto.getClubId() == null){
            return ResponseEntity.badRequest().build();
        }
        ClubEntity result = this.getClubSummaryUseCase.execute(dto);
        return ResponseEntity.ok(result);
    }@GetMapping("/listAll")
    public ResponseEntity<ClubResponse> listAllClubs(@RequestParam(value ="page", defaultValue = "0",required = false)int pageNo,
                                                     @RequestParam(value ="pageSize", defaultValue = "10",required = false)int pageSize){

        var result = this.listAllClubsUseCase.execute(pageNo,pageSize);

        return ResponseEntity.ok(result);
    }
}
