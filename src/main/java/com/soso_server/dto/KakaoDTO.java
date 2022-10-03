package com.soso_server.dto;

import java.sql.Timestamp;

public class KakaoDTO {
    // 카카오 아이디
    private int kakaoId;
    // 카카오 토큰
    private String kakaoToken;
    // 카카오 이메일
    private String kakaoEmail;
    // 카카오 닉네임
    private String kakaNickName;
    // 카카오 성별
    private String kakaoGender;
    // 카카오 생일
    private String kakaoBirthday;
    // 카카오 회원가입일
    private Timestamp kakaoRegisterDate;

    public int getKakaoId() {
        return kakaoId;
    }

    public void setKakaoId(int kakaoId) {
        this.kakaoId = kakaoId;
    }

    public String getKakaoToken() {
        return kakaoToken;
    }

    public void setKakaoToken(String kakaoToken) {
        this.kakaoToken = kakaoToken;
    }

    public String getKakaoEmail() {
        return kakaoEmail;
    }

    public void setKakaoEmail(String kakaoEmail) {
        this.kakaoEmail = kakaoEmail;
    }

    public String getKakaNickName() {
        return kakaNickName;
    }

    public void setKakaNickName(String kakaNickName) {
        this.kakaNickName = kakaNickName;
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
}
