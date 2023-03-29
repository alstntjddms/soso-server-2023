package com.soso_server.dto;

import java.sql.Timestamp;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKakaoId() {
        return kakaoId;
    }

    public void setKakaoId(String kakaoId) {
        this.kakaoId = kakaoId;
    }

    public String getKakaoAccessToken() {
        return kakaoAccessToken;
    }

    public void setKakaoAccessToken(String kakaoAccessToken) {
        this.kakaoAccessToken = kakaoAccessToken;
    }

    public String getKakaoRefreshToken() {
        return kakaoRefreshToken;
    }

    public void setKakaoRefreshToken(String kakaoRefreshToken) {
        this.kakaoRefreshToken = kakaoRefreshToken;
    }

    public String getKakaoEmail() {
        return kakaoEmail;
    }

    public void setKakaoEmail(String kakaoEmail) {
        this.kakaoEmail = kakaoEmail;
    }

    public String getKakaoNickName() {
        return kakaoNickName;
    }

    public void setKakaoNickName(String kakaoNickName) {
        this.kakaoNickName = kakaoNickName;
    }

    public String getKakaoGender() {
        return kakaoGender;
    }

    public void setKakaoGender(String kakaoGender) {
        this.kakaoGender = kakaoGender;
    }

    public String getKakaoBirthday() {
        return kakaoBirthday;
    }

    public void setKakaoBirthday(String kakaoBirthday) {
        this.kakaoBirthday = kakaoBirthday;
    }

    public Timestamp getKakaoRegisterDate() {
        return kakaoRegisterDate;
    }

    public void setKakaoRegisterDate(Timestamp kakaoRegisterDate) {
        this.kakaoRegisterDate = kakaoRegisterDate;
    }

    public Timestamp getKakaoLoginDate() {
        return kakaoLoginDate;
    }

    public void setKakaoLoginDate(Timestamp kakaoLoginDate) {
        this.kakaoLoginDate = kakaoLoginDate;
    }

    public boolean isKakaoMsgYn() {
        return kakaoMsgYn;
    }

    public void setKakaoMsgYn(boolean kakaoMsgYn) {
        this.kakaoMsgYn = kakaoMsgYn;
    }

    @Override
    public String toString() {
        return "KakaoDTO{" +
                "id=" + id +
                ", kakaoId='" + kakaoId + '\'' +
                ", kakaoAccessToken='" + kakaoAccessToken + '\'' +
                ", kakaoRefreshToken='" + kakaoRefreshToken + '\'' +
                ", kakaoEmail='" + kakaoEmail + '\'' +
                ", kakaoNickName='" + kakaoNickName + '\'' +
                ", kakaoGender='" + kakaoGender + '\'' +
                ", kakaoBirthday='" + kakaoBirthday + '\'' +
                ", kakaoRegisterDate=" + kakaoRegisterDate +
                ", kakaoLoginDate=" + kakaoLoginDate +
                '}';
    }
}
