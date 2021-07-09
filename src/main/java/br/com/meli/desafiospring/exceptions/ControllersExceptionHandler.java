package br.com.meli.desafiospring.exceptions;

import br.com.meli.desafiospring.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllersExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorDTO> handlerUserNotFound (UserNotFoundException exception) {
        ErrorDTO error = new ErrorDTO();
        error.setName("User Not Found");
        error.setErrorDetail(exception.getMessage());
        error.setHtttpStatusCode(400);
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorDTO> handlerMethodArgumentTypeMismatchException (MethodArgumentTypeMismatchException exception) {
        ErrorDTO error = new ErrorDTO();
        error.setName("Invalid parameter type.");
        error.setErrorDetail(exception.getMessage());
        error.setHtttpStatusCode(400);
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SelfFollowException.class})
    public ResponseEntity<ErrorDTO> handlerSelfFollowException (SelfFollowException exception) {
        ErrorDTO error = new ErrorDTO();
        error.setName("Self Follow Exception.");
        error.setErrorDetail(exception.getMessage());
        error.setHtttpStatusCode(400);
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SellerFollowException.class})
    public ResponseEntity<ErrorDTO> handlerSellerFollowException (SellerFollowException exception) {
        ErrorDTO error = new ErrorDTO();
        error.setName("Seller Follow Exception.");
        error.setErrorDetail(exception.getMessage());
        error.setHtttpStatusCode(400);
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RefollowException.class})
    public ResponseEntity<ErrorDTO> handlerSellerFollowException (RefollowException exception) {
        ErrorDTO error = new ErrorDTO();
        error.setName("Refollow Exception.");
        error.setErrorDetail(exception.getMessage());
        error.setHtttpStatusCode(400);
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDTO> handlerMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        ErrorDTO error = new ErrorDTO();
        error.setName("Method not valid exception.");
        List<String> listFields = exception.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toList());
        error.setErrorDetail("Invalid Fields: " + listFields.toString());
        error.setHtttpStatusCode(400);
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorDTO> handlerHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        ErrorDTO error = new ErrorDTO();
        error.setName("Http message not readable exception.");
        error.setErrorDetail("Invalid request body");
        error.setHtttpStatusCode(400);
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserDoesNotFollowException.class})
    public ResponseEntity<ErrorDTO> handlerUserDoesNotFollowException(UserDoesNotFollowException exception) {
        ErrorDTO error = new ErrorDTO();
        error.setName("User does not follow exception.");
        error.setErrorDetail(exception.getMessage());
        error.setHtttpStatusCode(400);
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }
}
