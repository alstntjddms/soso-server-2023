package com.soso_server.ra.itf;

import com.soso_server.dto.KakaoDTO;
import org.apache.ibatis.annotations.Param;

public interface KakaoRAO {
    public void registerKakao(KakaoDTO kakaoDTO);

    public KakaoDTO findOneKakao(@Param("kakaoId") String kakaoId, @Param("kakaoEmail")String kakaoEmail);
    }
