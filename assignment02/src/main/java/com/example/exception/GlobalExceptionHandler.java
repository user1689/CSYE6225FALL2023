package com.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;

/**
 * @title: GlobalExceptionHandler
 * @Author User1689
 * @Date: 2023/9/23 9:59 PM
 * @Version 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {DataBaseServiceDownException.class, ConnectException.class})
    @ResponseBody
    public ResponseEntity<String> dataBaseServiceDownExceptionHandler(HttpServletRequest req, DataBaseServiceDownException e) {
        log.error("database service down, check it plz!");
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                // .cacheControl(CacheControl.noCache())
                .header("Cache-Control", "no-cache, no-store, must-revalidate")
                .header("Pragma","no-cache")
                .header("X-Content-Type-Options", "nosniff")
                .body(null);
    }

}
