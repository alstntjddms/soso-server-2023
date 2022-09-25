package com.soso_server.tmp.Board;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostDTO {
    private Integer num;
    private String title;
    private Integer categorynum;
    private String writeBy;
    private String text;
    private Timestamp writeAt;
}
