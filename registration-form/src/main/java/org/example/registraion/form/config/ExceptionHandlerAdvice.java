package org.example.registraion.form.config;


import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

  @ExceptionHandler(value = {BindException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public RestControllerErrorMessage resourceNotFoundException(BindException ex, WebRequest request) {
    return new RestControllerErrorMessage(HttpStatus.BAD_REQUEST.value(),
        LocalDate.now(),
        ex.getMessage(),
        request.getDescription(false));
  }
}
