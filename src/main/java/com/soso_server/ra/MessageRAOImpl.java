package com.soso_server.ra;

import com.soso_server.ra.itf.MessageRAO;

public class MessageRAOImpl implements MessageRAO {

    private MessageRAO mapper;

    public void setMapper(MessageRAO mapper) {
        this.mapper = mapper;
    }

}
