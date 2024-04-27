package com.lucas.ClubManager.modules.users.controllers;

import com.lucas.ClubManager.modules.users.dto.LoginDTO;
import com.lucas.ClubManager.modules.users.dto.RegisterUserDTO;
import com.lucas.ClubManager.modules.users.dto.UserDTO;
import com.lucas.ClubManager.modules.users.dto.VerifyHasPlayerDTO;
import com.lucas.ClubManager.modules.users.entities.UserEntity;
import com.lucas.ClubManager.modules.users.useCases.GetUserUseCase;
import com.lucas.ClubManager.modules.users.useCases.LoginUseCase;
import com.lucas.ClubManager.modules.users.useCases.RegisterUserUseCase;
import com.lucas.ClubManager.modules.users.useCases.VerifyIfHasPlayerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/user")
public class UserController {

    private VerifyIfHasPlayerUseCase verifyIfHasPlayerUseCase;
    private RegisterUserUseCase registerUserUseCase;
    private LoginUseCase loginUseCase;

    private GetUserUseCase getUserUseCase;
    @Autowired
    public UserController(VerifyIfHasPlayerUseCase verifyIfHasPlayerUseCase, RegisterUserUseCase registerUserUseCase, GetUserUseCase getUserUseCase,LoginUseCase loginUseCase) {
        this.verifyIfHasPlayerUseCase = verifyIfHasPlayerUseCase;
        this.registerUserUseCase = registerUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDTO dto){
        var result = this.registerUserUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginWithEmail(@RequestBody LoginDTO dto){
        var result = this.loginUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") UUID id){
        var result = this.getUserUseCase.execute(id);

        return ResponseEntity.ok().body(result);
    }
}
