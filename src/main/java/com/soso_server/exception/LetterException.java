package com.soso_server.exception;

import java.util.HashMap;
import java.util.Map;

public class LetterException extends Exception{
    HashMap<Integer, String > exception = new HashMap<>();

    public LetterException() {
    }

    public LetterException(String message, int code) {
        exception.put(code, message);
    }
}
