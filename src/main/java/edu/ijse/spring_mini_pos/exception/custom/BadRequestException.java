package edu.ijse.spring_mini_pos.exception.custom;

public class BadRequestException extends  RuntimeException{

    public BadRequestException(String msg){
        super(msg);
    }
}
