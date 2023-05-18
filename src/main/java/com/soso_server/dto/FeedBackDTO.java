package com.soso_server.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class FeedBackDTO {
    public FeedBackDTO(String userId, String feedBack) {
        this.userId = userId;
        this.feedBack = feedBack;
    }

    private String userId;
    private String feedBack;
    private Timestamp regDate;
}
