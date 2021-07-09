package br.com.meli.desafiospring.exceptions;

public class UserDoesNotFollowException extends RuntimeException {
  public UserDoesNotFollowException() {
    super("You already not follow this user");
  }
}
