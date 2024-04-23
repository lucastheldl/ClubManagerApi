package com.lucas.ClubManager.modules.users.useCases;

import com.lucas.ClubManager.modules.users.dto.RegisterUserDTO;
import com.lucas.ClubManager.modules.users.entities.UserEntity;
import com.lucas.ClubManager.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {

    @Autowired
    private UserRepository userRepository;
    public String execute(RegisterUserDTO dto){
        try{
            if(dto.getUsername()== null){
                return "NOme de usuário invalido";
            }if(dto.getEmail()== null){
                return "Email de usuário invalido";
            }if(dto.getPassword() == null){
                return "Senha invalida";
            }
            UserEntity user = new UserEntity();
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            user.setUsername(dto.getUsername());

            if (dto.getImgURL() != null) {
                user.setImgURL(dto.getImgURL());
            }

            return "User registered successfully";
        }catch (Exception e){
            System.err.println("Error in registration player"+ e.getMessage());
            return "Error in registration";
        }

    }
}
