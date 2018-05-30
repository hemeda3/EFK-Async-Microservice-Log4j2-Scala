package com.ahmed.exceptions;

import com.ahmed.enums.StatfloErrors;
import javax.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Log4j2
public class ExceptionHandlingController {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
    ExceptionResponse response = new ExceptionResponse();
    response.setErrorCode(StatfloErrors.USER_NOT_EXIST.getCode());
    response.setErrorMessage(StatfloErrors.USER_NOT_EXIST.getDescription());
    log.error(response.toString());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InputValidationException.class)
  public ResponseEntity<ExceptionResponse> inputValidation(InputValidationException ex) {
    ExceptionResponse response = new ExceptionResponse();
    response.setErrorCode(StatfloErrors.INPUT_VALIDATION.getCode());
    response.setErrorMessage(StatfloErrors.INPUT_VALIDATION.getDescription());
    response.setException(ex.getLocalizedMessage());

    log.error(response.toString());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<ExceptionResponse> handleConstraintViolation(
      ConstraintViolationException ex, WebRequest request) {
    ExceptionResponse response = new ExceptionResponse();
    response.setErrorCode(StatfloErrors.DATABASE.getCode());
    response.setErrorMessage(StatfloErrors.DATABASE.getDescription());
    response.setException(ex.getLocalizedMessage());
    log.error(response.toString());

    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ExceptionResponse> emptyInput(HttpMessageNotReadableException ex) {
    ExceptionResponse response = new ExceptionResponse();
    response.setErrorCode(StatfloErrors.INPUT_VALIDATION.getCode());
    response.setErrorMessage(StatfloErrors.INPUT_VALIDATION.getDescription());
    response.setException(ex.getLocalizedMessage());

    log.error(response.toString());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ExceptionResponse> userAlreadyExist(DataIntegrityViolationException ex) {
    ExceptionResponse response = new ExceptionResponse();
    response.setErrorCode(StatfloErrors.DUPLICATE_USER.getCode());
    response.setErrorMessage(StatfloErrors.DUPLICATE_USER.getDescription());
    response.setException(ex.getLocalizedMessage());
    log.error(response.toString());

    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ExceptionResponse> handleGenericErrors(RuntimeException ex)
      throws RuntimeException {
    // Check for annotation
    if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
      // Rethrow Exception
      throw ex;
    } else {
      // Provide your general exception handling here
      ExceptionResponse response = new ExceptionResponse();
      response.setErrorCode(StatfloErrors.GENERIC_ERROR.getCode());
      response.setErrorMessage(StatfloErrors.GENERIC_ERROR.getDescription());
      response.setException(ex.getMessage());

      log.error(response.toString());

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
