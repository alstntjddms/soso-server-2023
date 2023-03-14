package com.soso_server.service.LogService;

import com.soso_server.dto.log.PageLogDTO;
import com.soso_server.ra.LogRAO.itf.PageLogRAO;
import com.soso_server.ra.itf.LetterRAO;
import com.soso_server.service.LogService.itf.PageLogService;
import org.springframework.stereotype.Service;

@Service
public class PageLogServiceImpl implements PageLogService {

    PageLogRAO rao;

    public void setRao(PageLogRAO rao) {
        this.rao = rao;
    }

    @Override
    public void registerPageLog(PageLogDTO pageLogDTO) {
        rao.registerPageLog(pageLogDTO);
    }
}