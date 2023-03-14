package com.soso_server.ra.LogRAO.itf;

import com.soso_server.dto.log.PageLogDTO;

public interface PageLogRAO {

    /**
     * HttpServletRequest의 Page Log를 저장한다.
     * @param HttpServletRequest
     * @return void
     */
    public void registerPageLog(PageLogDTO pageLogDTO);
}
