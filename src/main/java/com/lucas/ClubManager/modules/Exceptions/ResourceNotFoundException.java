package com.lucas.ClubManager.modules.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;
    public ResourceNotFoundException(String message){
        super(message);
    }

}
