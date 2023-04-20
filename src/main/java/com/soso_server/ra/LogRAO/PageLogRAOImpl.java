package com.soso_server.ra.LogRAO;

import com.soso_server.dto.log.PageLogDTO;
import com.soso_server.ra.LogRAO.itf.PageLogRAO;

public class PageLogRAOImpl implements PageLogRAO {
    private PageLogRAO mapper;

    public void setMapper(PageLogRAO mapper) {
        this.mapper = mapper;
    }

    @Override
    public void registerPageLog(PageLogDTO pageLogDTO) {
        mapper.registerPageLog(pageLogDTO);
    }
}
