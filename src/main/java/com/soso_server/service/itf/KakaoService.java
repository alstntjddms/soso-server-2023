package com.soso_server.service.itf;

import java.util.HashMap;

public interface KakaoService {

    public String getService(String accessCode);

    public HashMap<String, Object> getUserData(String access_Token);
}

