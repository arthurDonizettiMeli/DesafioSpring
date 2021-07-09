package br.com.meli.desafiospring.exceptions;

public class SellerFollowException extends RuntimeException{

    public SellerFollowException() {
        super("A seller can't follow anyone.");
    }
}
