package br.com.meli.desafiospring.exceptions;

public class PostNotFoundException extends RuntimeException {
  public PostNotFoundException(int id) {
    super("Post " + id + " not found.");
  }
}
