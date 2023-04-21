package com.soso_server.dto.log;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageLogDTO {
    String ipAddress;
    String url;

    public PageLogDTO(String ipAddress, String url) {
        this.ipAddress = ipAddress;
        this.url = url;
    }

}
