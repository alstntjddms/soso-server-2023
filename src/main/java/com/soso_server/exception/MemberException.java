package com.soso_server.exception;

import java.util.HashMap;

public class MemberException extends Exception{
    HashMap<Integer, String > exception = new HashMap<>();

    public MemberException() {
    }

    public MemberException(String message, int code) {
        exception.put(code, message);
    }
}
