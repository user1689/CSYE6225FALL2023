package com.example.service;

import com.example.exception.DataBaseServiceDownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @title: HealthCheckService
 * @Author User1689
 * @Date: 2023/9/23 9:37 PM
 * @Version 1.0
 */
@Service
public class HealthCheckService {

    @Value("${spring.datasource.driver-class-name}")
    private String className;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public Boolean healthCheck() {
        try {
            Class<?> clazz = Class.forName(className);
            Connection connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new DataBaseServiceDownException();
        }
        return true;
    }
}
