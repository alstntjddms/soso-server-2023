package com.soso_server.service.itf;

import com.soso_server.dto.KakaoDTO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

public interface KakaoService {

    /**
     * accessCode로 Access_Token을 가져온다.
     * @param accessCode
     * @return
     */
    public String getService(String accessCode);

    /**
     * Access_Token으로 유저 데이터를 가져온다.
     * @param access_Token
     * @param refresh_Token
     * @return
     */
    public KakaoDTO getUserData(String access_Token, String refresh_Token);

    public boolean checkScopes(String accessToken);

    public String refreshAccessToken(String refreshToken) throws IOException;

    public void withdraw(String accessToken) throws Exception ;
}

