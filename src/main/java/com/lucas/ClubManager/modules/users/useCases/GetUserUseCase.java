package com.lucas.ClubManager.modules.users.useCases;

import com.lucas.ClubManager.modules.users.dto.UserDTO;
import com.lucas.ClubManager.modules.users.entities.UserEntity;
import com.lucas.ClubManager.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetUserUseCase {


    private final UserRepository userRepository;
    @Autowired
    public GetUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public UserDTO execute(UUID id){

        try{
            Optional<UserEntity> optionalUser = this.userRepository.findById(id);

            if(optionalUser.isEmpty()){
                System.out.println("Usuário inválido");
                return null;
            }
            UserEntity user = optionalUser.get();

            var userDto = new UserDTO(user.getId(),user.getUsername(),user.getImgURL());

            return userDto;

        }catch (Exception e ){
            System.err.println("Error in getting the user" + e.getMessage());
            return null;
        }
    }
}
