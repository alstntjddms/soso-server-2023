package com.soso_server.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class LetterDTO {
    // 편지 아이디
    private String letterId;
    // 유저 아이디
    private String userId;
    // 편지 내용
    private String letterContent;
    // 편지 글꼴
    private String letterFont;
    // 편지 글씨 색깔
    private String letterFontColor;
    // 편지지
    private String letterPaper;
    // 작성자
    private String letterWriter;
    // 편지 아이콘
    private String letterIcon;
    // 편지 폰트사이즈
    private String letterFontSize;
    // 편지 편지 정렬
    private String letterTextAlign;
    // 편지 작성일
    private Timestamp letterWriteDate;
    // 읽음 여부
    private boolean letterReadYn;
    // 삭제 여부
    private boolean letterDelYn;
}
