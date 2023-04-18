package com.soso_server.ra.itf;

import com.soso_server.dto.KakaoDTO;
import com.soso_server.dto.MemberDTO;

import java.sql.Timestamp;
import java.util.List;

public interface MemberRAO {
    /**
     * Kakao 테이블의 id로 멤버를 등록한다.
     * @param memberDTO
     * @return
     */
    public int registerMember(MemberDTO memberDTO);

    /**
     * Kakao의 id로 KakaoDTO를 찾는다.
     * @param id
     * @return KakaoDTO
     */
    public KakaoDTO findKakaoByKakaoById(int id);

    /**
     * Member의 userId로 Member를 찾는다.
     * @param userId
     * @return MemberDTO
     */
    public MemberDTO findMemberByUserId(int userId);

    /**
     * Member의 id로 Member를 찾는다.
     * @param id
     * @return MemberDTO
     */
    public MemberDTO findMemberById(int id);

    /**
     * Member의 userId로 받은 편지수를 조회한다.
     * @param userId
     * return int
     */
    public int findMemberByLetterCount(int userId);

    /**
     * Member의 OpenDate를 조회한다.
     * @param userId
     * return String 날짜
     */
    public Timestamp findOpenDate(int userId);

    /**
     * Member의 OpenDate를 현재날짜 기준으로 등록한다.
     * @param userId
     * return String 날짜
     */
    public Timestamp registerOpenDate(int userId);

    /**
     * 모든 Member를 찾는다.
     * @param void
     * @return List<MemberDTO>
     */
    public List<MemberDTO> findMemberAll();

    public void modifyUserNickNameByUserId(MemberDTO memberDTO);


    public Timestamp refreshOpenDate(int userId);
}
