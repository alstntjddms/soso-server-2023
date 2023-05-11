package com.soso_server.ra.itf;

import com.soso_server.dto.KakaoDTO;

import java.util.List;

public interface MessageRAO {

    /**
     * 5분간 행성이 만료된 카카오를 조회한다.
     * @return List<KakaoDTO>
     */
    public List<KakaoDTO> findKakaoByDateExpired();

    /**
     * 행성을 만료한 후 새로 개설하지 않은지 3일이 자난 카카오를 조회한다.
     * @return List<KakaoDTO>
     */
    public List<KakaoDTO> findKakaoToDateExpiredByNotNewOpenDate();

    /**
     * 회원가입 후 행성을 개설하지 않은지 3일이 지난 카카오를 조회한다.
     * @return List<KakaoDTO>
     */
    public List<KakaoDTO> findKakaoToAfterRegisterByNotNewOpenDate();
}
