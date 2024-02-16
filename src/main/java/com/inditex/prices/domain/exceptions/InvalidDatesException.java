package com.inditex.prices.domain.exceptions;

public class InvalidDatesException extends RuntimeException{
    public InvalidDatesException(String message){
        super(message);
    }
}
