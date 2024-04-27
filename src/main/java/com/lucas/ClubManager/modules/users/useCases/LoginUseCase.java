package com.lucas.ClubManager.modules.users.useCases;

import com.lucas.ClubManager.modules.users.dto.LoginDTO;
import com.lucas.ClubManager.modules.users.dto.RegisterUserDTO;
import com.lucas.ClubManager.modules.users.entities.UserEntity;
import com.lucas.ClubManager.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {
    private UserRepository userRepository;
    @Autowired
    public LoginUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public String execute(LoginDTO dto){
        try{
            if(dto.getEmail()== null){
                return "Email de usuário invalido";
            }if(dto.getPassword() == null){
                return "Senha invalida";
            }

            UserEntity result = this.userRepository.findByUserEmail(dto.getEmail());
            if(!result.getPassword().equals(dto.getPassword())){
                return ("Error, email or password incorrect");
            }
            return ("User Logged successfully, id: " + result.getId().toString());
        }catch (Exception e){
            System.err.println("Error in registration player"+ e.getMessage());
            return "Error in registration";
        }
    }
}
