package com.lucas.ClubManager.modules.players.controllers;

import com.lucas.ClubManager.modules.players.dto.PlayerDTO;
import com.lucas.ClubManager.modules.players.useCases.CreatePlayerUseCase;
import com.lucas.ClubManager.modules.players.useCases.GetPlayerUseCase;
import com.lucas.ClubManager.modules.players.useCases.ListAllPlayersUseCase;
import com.lucas.ClubManager.modules.players.useCases.UpdatePlayerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/player")
public class PlayersController {

    private CreatePlayerUseCase createPlayerUseCase;
    private ListAllPlayersUseCase listAllPlayersUseCase;
    private GetPlayerUseCase getPlayerUseCase;
    private UpdatePlayerUseCase updatePlayerUseCase;
    @Autowired
    public PlayersController(CreatePlayerUseCase createPlayerUseCase, ListAllPlayersUseCase listAllPlayersUseCase, GetPlayerUseCase getPlayerUseCase, UpdatePlayerUseCase updatePlayerUseCase) {
        this.createPlayerUseCase = createPlayerUseCase;
        this.listAllPlayersUseCase = listAllPlayersUseCase;
        this.getPlayerUseCase = getPlayerUseCase;
        this.updatePlayerUseCase = updatePlayerUseCase;
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
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> listAllPlayers(@PathVariable("id") UUID playerId){
        if(playerId == null){
            return ResponseEntity.badRequest().build();
        }
        var result = this.getPlayerUseCase.execute(playerId);
        return result;
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO dto, @PathVariable("id") UUID playerId){

        if(playerId == null){
            return ResponseEntity.badRequest().build();
        }
        var result = this.updatePlayerUseCase.execute(dto,playerId);
        return result;
    }
}
