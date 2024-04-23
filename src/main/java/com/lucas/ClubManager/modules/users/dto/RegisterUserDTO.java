package com.lucas.ClubManager.modules.users.dto;

import lombok.Data;

@Data
public class RegisterUserDTO {

    private String email;
    private String password;
    private String username;
    private String imgURL;
}
