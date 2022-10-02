package com.soso_server.ra;

import com.soso_server.ra.itf.KakaoRAO;
import com.soso_server.ra.itf.MemberRAO;

public class MemberRAOImpl implements MemberRAO {

    private MemberRAO mapper;

    public void setMapper(MemberRAO mapper) {
        this.mapper = mapper;
    }
}
