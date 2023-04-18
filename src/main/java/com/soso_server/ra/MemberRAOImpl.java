package com.soso_server.ra;

import com.soso_server.dto.KakaoDTO;
import com.soso_server.dto.MemberDTO;
import com.soso_server.ra.itf.KakaoRAO;
import com.soso_server.ra.itf.MemberRAO;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public class MemberRAOImpl implements MemberRAO {

    private MemberRAO mapper;

    public void setMapper(MemberRAO mapper) {
        this.mapper = mapper;
    }

    @Override
    public int registerMember(MemberDTO memberDTO) {
        return mapper.registerMember(memberDTO);
    }

    @Override
    public KakaoDTO findKakaoByKakaoById(int id) {
        return mapper.findKakaoByKakaoById(id);
    }

    @Override
    public MemberDTO findMemberByUserId(int userId) {
        return mapper.findMemberByUserId(userId);
    }

    @Override
    public MemberDTO findMemberById(int id) {
        return mapper.findMemberById(id);
    }

    @Override
    public int findMemberByLetterCount(int userId) {
        return mapper.findMemberByLetterCount(userId);
    }

    @Override
    public Timestamp findOpenDate(int userId) {
        return mapper.findOpenDate(userId);
    }

    @Override
    public Timestamp registerOpenDate(int userId) {
        return mapper.registerOpenDate(userId);
    }

    @Override
    public List<MemberDTO> findMemberAll() {
        return mapper.findMemberAll();
    }

    @Override
    public void modifyUserNickNameByUserId(MemberDTO memberDTO) {
        mapper.modifyUserNickNameByUserId(memberDTO);
    }

    @Override
    public Timestamp refreshOpenDate(int userId) {
        return mapper.refreshOpenDate(userId);
    }

}
