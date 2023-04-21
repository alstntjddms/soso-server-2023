package com.soso_server.service.itf;

public interface AuthService {

    public String checkCode(String code);

    public String checkAuthKey(String authKey);
    
}

