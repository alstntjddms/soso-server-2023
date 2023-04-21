package com.soso_server.ra;

import com.soso_server.dto.AuthDTO;
import com.soso_server.ra.itf.AuthRAO;

public class AuthRAOImpl implements AuthRAO {

    private AuthRAO mapper;

    public void setMapper(AuthRAO mapper) {
            this.mapper = mapper;
        }

    @Override
    public AuthDTO selectAuth(String code) {
        return mapper.selectAuth(code);
    }

    @Override
    public AuthDTO checkAuthKey(String authKey) {
        return mapper.checkAuthKey(authKey);
    }
}
