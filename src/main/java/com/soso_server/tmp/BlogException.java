package com.soso_server.tmp;

import java.util.HashMap;
import java.util.Map;

public class BlogException extends Exception{
    private String code = null;

    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("message", getMessage());
        return map;
    }

    public BlogException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BlogException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
