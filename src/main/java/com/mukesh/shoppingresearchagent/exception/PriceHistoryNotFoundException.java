package com.mukesh.shoppingresearchagent.exception;

public class PriceHistoryNotFoundException extends RuntimeException{
    public PriceHistoryNotFoundException (String message){
        super(message);
    }
}
