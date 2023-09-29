package com.example.rest;

import cn.hutool.core.util.BooleanUtil;
import com.example.exception.DataBaseServiceDownException;
import com.example.service.HealthCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @title: HealthResource
 * @Author User1689
 * @Date: 2023/9/23 9:12 PM
 * @Version 1.0
 */
@Slf4j
@RestController
public class HealthCheckEndpoint {

    @Autowired
    private HealthCheckService healthCheckService;

    @GetMapping("/healthz")
    public ResponseEntity<String> healthCheck() throws SQLException, ClassNotFoundException {
        Boolean isHealth = healthCheckService.healthCheck();
        if (BooleanUtil.isFalse(isHealth)) throw new DataBaseServiceDownException();
        return ResponseEntity
                .status(HttpStatus.OK)
                // .cacheControl(CacheControl.noCache())
                .header("Cache-Control", "no-cache, no-store, must-revalidate")
                .header("Pragma","no-cache")
                .header("X-Content-Type-Options", "nosniff")
                .body(null);
    }
}
