package com.soso_server.service.itf;

import com.soso_server.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    /**
     * Kakao 테이블의 id로 멤버를 등록한다.
     * @param id
     * @return
     */
    String registerMember(String id) throws Exception;

    /**
     * 모든 Member를 찾는다.
     * @param void
     * @return List<MemberDTO>
     */
    List<MemberDTO> findMemberAll();

    /**
     * Member를 찾는다
     * @param userId
     * @return MemberDTO
     */
    MemberDTO findMemberByUserId(String userId) throws Exception;
}
