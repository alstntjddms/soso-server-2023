package com.soso_server.service;

import com.soso_server.ra.itf.KakaoRAO;
import com.soso_server.ra.itf.LetterRAO;
import com.soso_server.service.itf.KakaoService;
import org.springframework.stereotype.Service;

@Service
public class KakaoServiceImpl implements KakaoService {

    KakaoRAO rao;

    public void setRao(KakaoRAO rao) {
        this.rao = rao;
    }


    @Override
    public String getService(String accessCode) {

        return null;
    }
}
