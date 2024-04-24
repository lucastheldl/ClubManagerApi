package com.lucas.ClubManager.modules.users.useCases;

import com.lucas.ClubManager.modules.users.dto.VerifyHasPlayerDTO;
import com.lucas.ClubManager.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasPlayerUseCase {

    @Autowired
    private UserRepository userRepository;


    public boolean execute(VerifyHasPlayerDTO dto){
        var result = this.userRepository.findByUserEmailAndPlayerId(dto.getEmail(), dto.getPlayerName());

        if(!result.isEmpty()){
            return true;
        }
        return false;
    }

}
