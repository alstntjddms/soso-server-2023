package com.soso_server.service.itf;

import com.soso_server.dto.MemberDTO;
import com.soso_server.exception.MemberException;

import java.sql.Timestamp;
import java.util.HashMap;
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

    public String modifyUserNickNameByUserId(String userId, String userNickName);

    public int findMemberByLetterCount(String userId) throws MemberException;

    public Timestamp registerOpenDate(String userId) throws Exception;

    public Timestamp findOpenDate(String userId) throws Exception;

    /**
     * 암호화된 userId를 외부공개용 userId로 다시 암호화한다.
     * @param userId
     * @return String
     * @Throws Exception
     */
    public String changeExternalUserId(String userId) throws Exception;

    /**
     * 외부공개용 userId를 다시 복호화해서 닉네임, 오픈데이트 받아온다.
     * @param userId
     * @return MemberDTO
     * @Throws Exception
     */
    public MemberDTO infoByExternalUserId(String userId);

    /**
     * 외부공개용 userId를 다시 복호화해서 받은 편지 개수 받아온다.
     * @param userId
     * @return MemberDTO
     * @Throws Exception
     */
    public Integer findLetterCountByExternalUserId(String userId);
}
