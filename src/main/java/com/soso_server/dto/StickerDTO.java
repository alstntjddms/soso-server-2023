package com.soso_server.dto;

public class StickerDTO {
    // 스티커 아이디
    private int stickerId;
    // 편지지 아이디
    private int letterId;
    // 스티커 아이콘
    private String stickerIcon;
    // 스티커 X좌표
    private String stickerX;
    // 스티커 Y좌표
    private String stickerY;

    public int getStickerId() {
        return stickerId;
    }

    public void setStickerId(int stickerId) {
        this.stickerId = stickerId;
    }

    public int getLetterId() {
        return letterId;
    }

    public void setLetterId(int letterId) {
        this.letterId = letterId;
    }

    public String getStickerIcon() {
        return stickerIcon;
    }

    public void setStickerIcon(String stickerIcon) {
        this.stickerIcon = stickerIcon;
    }

    public String getStickerX() {
        return stickerX;
    }

    public void setStickerX(String stickerX) {
        this.stickerX = stickerX;
    }

    public String getStickerY() {
        return stickerY;
    }

    public void setStickerY(String stickerY) {
        this.stickerY = stickerY;
    }
}
