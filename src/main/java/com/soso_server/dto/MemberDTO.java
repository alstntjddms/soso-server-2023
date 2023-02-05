package com.soso_server.dto;

import java.sql.Timestamp;

public class MemberDTO {
    // 유저 아이디
    private int userId;
    // 카카오 아이디
    private int id;
    // 유저 별명
    private String userNickName;
    // 오픈 날짜
    private Timestamp userOpenDate;
    // 사용자 생성 날짜
    private Timestamp userDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Timestamp getUserOpenDate() {
        return userOpenDate;
    }

    public void setUserOpenDate(Timestamp userOpenDate) {
        this.userOpenDate = userOpenDate;
    }

    public Timestamp getUserDate() {
        return userDate;
    }

    public void setUserDate(Timestamp userDate) {
        this.userDate = userDate;
    }
}
