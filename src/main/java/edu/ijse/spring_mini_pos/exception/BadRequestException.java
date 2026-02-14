package edu.ijse.spring_mini_pos.exception;

public class BadRequestException extends  RuntimeException{

    public BadRequestException(String msg){
        super(msg);
    }
}
