package com.application.inditex.prices.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Class that handles exception and transform response to an error.
 *
 * @author chema
 */
@ControllerAdvice
@Slf4j
public class PricesExceptionHandler {

    @ExceptionHandler(NullValueException.class)
    public ResponseEntity<String> handleNullValueException(NullValueException e) {
        log.error("Null value for: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidDatesException.class)
    public ResponseEntity<String> handleInvalidDatesException(InvalidDatesException e) {
        log.error("Error occurred with dates values: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<String> handleParseException(ParseException e) {
        log.error("Error occurred parsing values: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred parsing data: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("Error occurred: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}
