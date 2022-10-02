package com.soso_server.service;

import com.soso_server.ra.itf.KakaoRAO;
import com.soso_server.ra.itf.MemberRAO;
import com.soso_server.service.itf.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    MemberRAO rao;

    public void setRao(MemberRAO rao) {
        this.rao = rao;
    }


}
