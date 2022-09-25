package com.soso_server.dto;

import java.sql.Timestamp;

public class MemberDTO {
    // 유저 아이디
    private int userId;
    // 카카오 아이디
    private int kakaoId;
    // 유저 별명
    private String userNickName;
    // 편지받은 수
    private int userGetAll;
    // 오픈 날짜
    private Timestamp userOpenDate;
    // 사용자 생성 날짜
    private Timestamp userData;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getKakaoId() {
        return kakaoId;
    }

    public void setKakaoId(int kakaoId) {
        this.kakaoId = kakaoId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public int getUserGetAll() {
        return userGetAll;
    }

    public void setUserGetAll(int userGetAll) {
        this.userGetAll = userGetAll;
    }

    public Timestamp getUserOpenDate() {
        return userOpenDate;
    }

    public void setUserOpenDate(Timestamp userOpenDate) {
        this.userOpenDate = userOpenDate;
    }

    public Timestamp getUserData() {
        return userData;
    }

    public void setUserData(Timestamp userData) {
        this.userData = userData;
    }
}
