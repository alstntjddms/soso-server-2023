package com.soso_server.service.itf;

import com.soso_server.dto.MemberDTO;
import com.soso_server.exception.MemberException;

import java.util.List;

public interface MemberService {
    /**
     * Kakao 테이블의 id로 멤버를 등록한다.
     * @param id
     * @return
     */
    public String registerMember(String id) throws Exception;

    /**
     * 모든 Member를 찾는다.
     * @param void
     * @return List<MemberDTO>
     */
    public List<MemberDTO> findMemberAll();

    /**
     * Member를 찾는다
     * @param userId
     * @return MemberDTO
     */
    public MemberDTO findMemberByUserId(String userId) throws Exception;

    public int findMemberByLetterCount(String userId) throws MemberException;
}
