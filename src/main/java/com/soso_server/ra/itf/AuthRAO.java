package com.soso_server.ra.itf;

import com.soso_server.dto.AuthDTO;

public interface AuthRAO {

    public AuthDTO selectAuth(String code);

    public AuthDTO checkAuthKey(String authKey);

}
