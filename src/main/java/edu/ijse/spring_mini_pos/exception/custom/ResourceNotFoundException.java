package edu.ijse.spring_mini_pos.exception.custom;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
