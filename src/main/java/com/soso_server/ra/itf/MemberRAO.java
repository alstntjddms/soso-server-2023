package com.soso_server.ra.itf;

import com.soso_server.dto.KakaoDTO;
import com.soso_server.dto.MemberDTO;

import java.util.List;

public interface MemberRAO {
    /**
     * Kakao 테이블의 id로 멤버를 등록한다.
     * @param memberDTO
     * @return
     */
    int registerMember(MemberDTO memberDTO);

    /**
     * Kakao의 id로 KakaoDTO를 찾는다.
     * @param id
     * @return KakaoDTO
     */
    KakaoDTO findKakaoByKakaoById(int id);

    /**
     * Member의 userId로 Member를 찾는다.
     * @param userId
     * @return MemberDTO
     */
    MemberDTO findMemberByUserId(int userId);

    /**
     * Member의 id로 Member를 찾는다.
     * @param id
     * @return MemberDTO
     */
    MemberDTO findMemberById(int id);

    /**
     * 모든 Member를 찾는다.
     * @param void
     * @return List<MemberDTO>
     */
    List<MemberDTO> findMemberAll();
}
