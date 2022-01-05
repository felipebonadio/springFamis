package br.com.famis.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Object> handleAnyException(IdNotFoundException e, WebRequest request){
        String message = "Id não encontrado";
        return handleExceptionInternal(e, message, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request);
    }
}
