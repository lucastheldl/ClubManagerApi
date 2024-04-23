package com.lucas.ClubManager.modules.users.controllers;

import com.lucas.ClubManager.modules.users.dto.RegisterUserDTO;
import com.lucas.ClubManager.modules.users.dto.VerifyHasPlayerDTO;
import com.lucas.ClubManager.modules.users.entities.UserEntity;
import com.lucas.ClubManager.modules.users.useCases.GetUserUseCase;
import com.lucas.ClubManager.modules.users.useCases.RegisterUserUseCase;
import com.lucas.ClubManager.modules.users.useCases.VerifyIfHasPlayerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private VerifyIfHasPlayerUseCase verifyIfHasPlayerUseCase;
    @Autowired
    private RegisterUserUseCase registerUserUseCase;
    @Autowired
    private GetUserUseCase getUserUseCase;

    @PostMapping("/verifyIfHasPlayer")
    public String verifyIfHasPlayer(@RequestBody VerifyHasPlayerDTO verifyHasPlayerDTO){
        var result = this.verifyIfHasPlayerUseCase.execute(verifyHasPlayerDTO);

        if(result){
            return "Já possui o jogador";
        }
        //System.out.println(result);
        return "Não possui o jogador";
    }
    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterUserDTO dto){
        var result = this.registerUserUseCase.execute(dto);


        //System.out.println(result);
        return result;
    }@GetMapping("/user/{id}")
    public UserEntity getUser(@PathVariable("id") UUID id){
        var result = this.getUserUseCase.execute(id);


        //System.out.println(result);
        return result;
    }
}
