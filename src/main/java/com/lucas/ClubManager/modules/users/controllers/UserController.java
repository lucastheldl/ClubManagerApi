package com.lucas.ClubManager.modules.users.controllers;

import com.lucas.ClubManager.modules.users.dto.VerifyHasPlayerDTO;
import com.lucas.ClubManager.modules.users.useCases.VerifyIfHasPlayerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private VerifyIfHasPlayerUseCase verifyIfHasPlayerUseCase;

    @PostMapping("/verifyIfHasPlayer")
    public String verifyIfHasPlayer(@RequestBody VerifyHasPlayerDTO verifyHasPlayerDTO){
        var result = this.verifyIfHasPlayerUseCase.execute(verifyHasPlayerDTO);

        if(result){
            return "Já possui o jogador";
        }
        //System.out.println(result);
        return "Não possui o jogador";
    }
}
