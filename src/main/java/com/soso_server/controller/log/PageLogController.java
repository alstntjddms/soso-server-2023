package com.soso_server.controller.log;

import com.soso_server.service.LogService.itf.PageLogService;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PageLogController {

    private static final Logger logger = Logger.getLogger(PageLogController.class);

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
            pageLogService.registerPageLog(request);
        }catch (Exception e){
            logger.info("[registerPageLog] Exception = " + e.getMessage());
        }
    }

}
