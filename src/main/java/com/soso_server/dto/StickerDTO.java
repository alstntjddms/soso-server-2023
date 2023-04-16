package com.soso_server.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
}
