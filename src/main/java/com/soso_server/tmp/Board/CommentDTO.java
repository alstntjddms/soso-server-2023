package com.soso_server.tmp.Board;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDTO {
    private Integer num;
    private String comment;
    private Timestamp commentAt;
}
