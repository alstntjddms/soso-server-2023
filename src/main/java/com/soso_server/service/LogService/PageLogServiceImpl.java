package com.soso_server.service.LogService;

import com.soso_server.dto.log.PageLogDTO;
import com.soso_server.ra.LogRAO.itf.PageLogRAO;
import com.soso_server.ra.itf.LetterRAO;
import com.soso_server.service.LogService.itf.PageLogService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PageLogServiceImpl implements PageLogService {

    PageLogRAO rao;

    public void setRao(PageLogRAO rao) {
        this.rao = rao;
    }

    @Override
    public void registerPageLog(HttpServletRequest request) {
        try {
            String ipAddress = request.getHeader("X-Forwarded-For");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            rao.registerPageLog(new PageLogDTO(
                    ipAddress,
                    request.getQueryString()
            ));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}