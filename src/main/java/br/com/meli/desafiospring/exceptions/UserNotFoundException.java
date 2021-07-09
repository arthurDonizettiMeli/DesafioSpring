package br.com.meli.desafiospring.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int id) {
        super("User " + id + " not found.");
    }
}
