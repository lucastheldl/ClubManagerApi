package com.lucas.ClubManager.modules.users.useCases;

import com.lucas.ClubManager.modules.users.dto.VerifyHasPlayerDTO;
import com.lucas.ClubManager.modules.users.repositories.UserPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasPlayerUseCase {

    @Autowired
    private UserPlayerRepository userPlayerRepository;
    public boolean execute(VerifyHasPlayerDTO dto){
        var result = this.userPlayerRepository.findByUserEmailAndPlayerId(dto.getEmail(), dto.getPlayerName());

        if(!result.isEmpty()){
            return true;
        }
        return false;
    }

}
