package com.inditex.prices.domain.exceptions;


import org.apache.el.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PricesExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(PricesExceptionHandler.class);

    @ExceptionHandler(NullValueException.class)
    public ResponseEntity<String> handleNullValueException(NullValueException e) {
        LOG.error("Null value for: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidDatesException.class)
    public ResponseEntity<String> handleInvalidDatesException(InvalidDatesException e) {
        LOG.error("Error occurred with dates values: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<String> handleParseException(ParseException e) {
        LOG.error("Error occurred parsing values: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred parsing data: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        LOG.error("Error occurred: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}
