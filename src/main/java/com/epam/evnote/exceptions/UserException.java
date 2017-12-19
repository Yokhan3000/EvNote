package com.epam.evnote.exceptions;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
public class UserException extends RuntimeException {
    private String message;

  public UserException(String message) {
    this.message = message;
  }
}
