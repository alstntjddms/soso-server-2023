package com.soso_server.dto;

import java.sql.Timestamp;

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


    public String getLetterId() {
        return letterId;
    }

    public void setLetterId(String letterId) {
        this.letterId = letterId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }

    public String getLetterFont() {
        return letterFont;
    }

    public void setLetterFont(String letterFont) {
        this.letterFont = letterFont;
    }

    public String getLetterFontColor() {
        return letterFontColor;
    }

    public void setLetterFontColor(String letterFontColor) {
        this.letterFontColor = letterFontColor;
    }

    public String getLetterPaper() {
        return letterPaper;
    }

    public void setLetterPaper(String letterPaper) {
        this.letterPaper = letterPaper;
    }

    public String getLetterWriter() {
        return letterWriter;
    }

    public void setLetterWriter(String letterWriter) {
        this.letterWriter = letterWriter;
    }

    public String getLetterIcon() {
        return letterIcon;
    }

    public void setLetterIcon(String letterIcon) {
        this.letterIcon = letterIcon;
    }

    public String getLetterFontSize() {
        return letterFontSize;
    }

    public void setLetterFontSize(String letterFontSize) {
        this.letterFontSize = letterFontSize;
    }

    public String getLetterTextAlign() {
        return letterTextAlign;
    }

    public void setLetterTextAlign(String letterTextAlign) {
        this.letterTextAlign = letterTextAlign;
    }

    public Timestamp getLetterWriteDate() {
        return letterWriteDate;
    }

    public void setLetterWriteDate(Timestamp letterWriteDate) {
        this.letterWriteDate = letterWriteDate;
    }

    public boolean isLetterReadYn() {
        return letterReadYn;
    }

    public void setLetterReadYn(boolean letterReadYn) {
        this.letterReadYn = letterReadYn;
    }

    public boolean isLetterDelYn() {
        return letterDelYn;
    }

    public void setLetterDelYn(boolean letterDelYn) {
        this.letterDelYn = letterDelYn;
    }
}
