package br.com.meli.desafiospring.exceptions;

public class RefollowException extends RuntimeException{
    public RefollowException() {
        super("Already follow this user.");
    }
}
