package com.example.service;

import com.example.model.Login;

import java.util.Map;

/**
 * @author: 谢佳辉
 * @date 2021/1/12 2:46 下午
 */
public interface TokenService {
    public String createToken(Login login);
    public Map<String, com.example.model.Login> queryByToken(String token);
}
