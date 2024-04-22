package com.lucas.ClubManager.modules.players.controllers;

import com.lucas.ClubManager.modules.players.entities.PlayerEntity;
import com.lucas.ClubManager.modules.players.useCases.CreatePlayerUseCase;
import com.lucas.ClubManager.modules.players.useCases.ListAllPlayersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayersController {

    @Autowired
    private CreatePlayerUseCase createPlayerUseCase;
    @Autowired
    private ListAllPlayersUseCase listAllPlayersUseCase;

    @PostMapping("/createPlayer")
    public ResponseEntity<String> createPlayer(@RequestBody PlayerEntity player){
        var result = this.createPlayerUseCase.execute(player);
        if(!result){
            return ResponseEntity.internalServerError().body("Falha na operação");
        }
        return ResponseEntity.ok("result");
    }

    @GetMapping("/listAllPlayers")
    public ResponseEntity<List<PlayerEntity>> listAllPlayers(){

        var result = this.listAllPlayersUseCase.execute();
        return ResponseEntity.ok(result);
    }
}
