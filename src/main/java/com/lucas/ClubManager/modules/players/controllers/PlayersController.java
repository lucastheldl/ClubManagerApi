package com.lucas.ClubManager.modules.players.controllers;

import com.lucas.ClubManager.modules.players.dto.PlayerDTO;
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

    private CreatePlayerUseCase createPlayerUseCase;
    private ListAllPlayersUseCase listAllPlayersUseCase;
    @Autowired
    public PlayersController(CreatePlayerUseCase createPlayerUseCase, ListAllPlayersUseCase listAllPlayersUseCase) {
        this.createPlayerUseCase = createPlayerUseCase;
        this.listAllPlayersUseCase = listAllPlayersUseCase;
    }

    @PostMapping("/createPlayer")
    public ResponseEntity<String> createPlayer(@RequestBody PlayerDTO dto){
        if (dto.getPlayerName() == null) {
            return ResponseEntity.badRequest().body("Player name invalid");
        }
        var result = this.createPlayerUseCase.execute(dto);
        if(!result){
            return ResponseEntity.internalServerError().body("Falha na operação");
        }
        return ResponseEntity.ok("result");
    }

    @GetMapping("/listAllPlayers")
    public ResponseEntity<List<PlayerDTO>> listAllPlayers(){

        var result = this.listAllPlayersUseCase.execute();
        return ResponseEntity.ok(result);
    }
}
