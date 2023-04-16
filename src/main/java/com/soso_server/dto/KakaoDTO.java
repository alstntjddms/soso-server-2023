package com.soso_server.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class KakaoDTO {
    private int id;
    // 카카오 아이디
    private String kakaoId;
    // 카카오 허용 토큰
    private String kakaoAccessToken;
    // 카카오 새로고침 토큰
    private String kakaoRefreshToken;
    // 카카오 이메일
    private String kakaoEmail;
    // 카카오 닉네임
    private String kakaoNickName;
    // 카카오 성별
    private String kakaoGender;
    // 카카오 생일
    private String kakaoBirthday;
    // 카카오 회원가입일
    private Timestamp kakaoRegisterDate;
    // 카카오 최종로그인날짜
    private Timestamp kakaoLoginDate;
    // 카카오 메세지 동의
    private boolean kakaoMsgYn;
    // 동의항목 눌름 여부
    private boolean kakaoScopeCheck;

}
