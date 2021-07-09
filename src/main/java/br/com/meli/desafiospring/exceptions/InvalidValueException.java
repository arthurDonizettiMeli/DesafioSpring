package br.com.meli.desafiospring.exceptions;

public class InvalidValueException extends RuntimeException {
  public InvalidValueException() {
    super("Value should be bigger than 0");
  }
}
