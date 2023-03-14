package com.soso_server.controller.log;

import com.soso_server.dto.log.PageLogDTO;
import com.soso_server.service.LogService.PageLogServiceImpl;
import com.soso_server.service.LogService.itf.PageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PageLogController {

    @Autowired
    PageLogService pageLogService;

    /**
     * HttpServletRequest의 Page Log를 저장한다.
     * @param HttpServletRequest
     * @return void
     */
    @GetMapping("/request/log")
    public void registerPageLog(HttpServletRequest request) {
        try {
            String ipAddress = request.getHeader("X-Forwarded-For");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            pageLogService.registerPageLog(new PageLogDTO(
                    ipAddress,
                    request.getQueryString()
            ));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
