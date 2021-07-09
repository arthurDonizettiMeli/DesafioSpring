package br.com.meli.desafiospring.exceptions;

public class SelfFollowException extends RuntimeException{

    public SelfFollowException () {
        super("You can't follow yourself.");
    }
}
