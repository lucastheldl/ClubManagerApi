package com.lucas.ClubManager.modules.users.useCases;

import com.lucas.ClubManager.modules.users.dto.VerifyHasPlayerDTO;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasPlayerUseCase {

    public boolean execute(VerifyHasPlayerDTO dto){
        if(dto.getEmail().equals("lucas2@gmail.com") && dto.getPlayerId().equals("jaja")){
            return true;
        }
        return false;
    }

}
