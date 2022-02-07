package com.hs.hsblog_backend.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hs
 * @Date 2022/2/6 21:16
 */
@RestController
@RequestMapping("/admin")
public class OperationLogController {
    @Autowired
    OperationLogService operationLogService;


}
