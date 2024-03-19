package com.lucas.ClubManager.modules.users.controllers;

import com.lucas.ClubManager.modules.users.dto.VerifyHasPlayerDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/verifyIfHasPlayer")
    public void verifyIfHasPlayer(@RequestBody VerifyHasPlayerDTO verifyHasPlayerDTO){
        System.out.println(verifyHasPlayerDTO);
    }
}
