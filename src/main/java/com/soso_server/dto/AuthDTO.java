package com.soso_server.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthDTO {
    String id;
    String name;
    String authKey;
    String code;
}
