package com.soso_server.ra.itf;

import com.soso_server.dto.KakaoDTO;

import java.util.List;

public interface MessageRAO {

    /**
     * 5분간 행성이 만료된 카카오를 조회한다.
     * @return
     */
    public List<KakaoDTO> findKakaoByDateExpired();
}
