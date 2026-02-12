package edu.ijse.spring_mini_pos.exception;

import edu.ijse.spring_mini_pos.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<String>> generalExceptionHandler(Exception e){
       return new ResponseEntity<>(new APIResponse<>(
               HttpStatus.INTERNAL_SERVER_ERROR.value(),
               "internal server error",
               null
       ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<APIResponse<String>> nullExceptionHandler(Exception e){
        return new ResponseEntity<>(new APIResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "null values are not allowed",
                e.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){

    }
}
