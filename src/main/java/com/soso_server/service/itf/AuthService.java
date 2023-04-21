package com.soso_server.service.itf;

import com.soso_server.dto.AuthDTO;

public interface AuthService {

    public AuthDTO selectAuth(String code);

    public AuthDTO checkAuthKey(String authKey);
    
}

