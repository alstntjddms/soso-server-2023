package com.soso_server.service.LogService.itf;

import com.soso_server.dto.log.PageLogDTO;

import javax.servlet.http.HttpServletRequest;

public interface PageLogService {

    /**
     * HttpServletRequest의 Page Log를 저장한다.
     * @param HttpServletRequest
     * @return void
     */
    public void registerPageLog(HttpServletRequest request);
}
