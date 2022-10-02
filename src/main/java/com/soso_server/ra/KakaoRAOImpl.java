package com.soso_server.ra;

import com.soso_server.ra.itf.KakaoRAO;
import com.soso_server.ra.itf.LetterRAO;

public class KakaoRAOImpl implements KakaoRAO {

    private KakaoRAO mapper;

    public void setMapper(KakaoRAO mapper) {
        this.mapper = mapper;
    }

}
