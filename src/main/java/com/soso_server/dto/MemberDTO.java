package com.soso_server.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class MemberDTO {
    // 유저 아이디
    private String userId;
    // 카카오 아이디
    private int id;
    // 유저 별명
    private String userNickName;
    // 오픈 날짜
    private Timestamp userOpenDate;
    // 사용자 생성 날짜
    private Timestamp userDate;
}
