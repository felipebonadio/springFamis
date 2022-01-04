package br.com.famis.exceptions;

public class IdNotFound extends RuntimeException{
    public IdNotFound(String message){
        super(message);
    }
}
