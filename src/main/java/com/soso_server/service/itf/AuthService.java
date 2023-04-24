package com.soso_server.service.itf;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    public Boolean createCookie(String code, HttpServletResponse response);

    /**
     * JwtToken 검증 로직
     * @param request
     * @return
     */
    public Boolean checkJwtToken(HttpServletRequest request);
    
}

