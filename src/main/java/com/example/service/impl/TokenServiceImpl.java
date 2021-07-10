package com.example.service.impl;

/**
 * @author: 谢佳辉
 * @date 2021/1/12 2:45 下午
 */


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.LoginMapper;
import com.example.model.Login;
import com.example.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service("tokenService")
public class TokenServiceImpl  implements TokenService {
    /**
     * 12小时后过期
     */
    private final static int EXPIRE = 3600 * 12;
    @Autowired
    LoginMapper loginMapper;
    @Override
    public Map<String, Login> queryByToken(String token) {
         Map<String,Login> map= new HashMap<>();
          map .put(token,loginMapper.selectOne(new QueryWrapper<Login>().eq("Token", token)));
       return map;
    }

    @Override
    public String createToken(Login login) {

        //生成token
        String token = generateToken();
        Map<String,Login> map = new HashMap<>();
        map.put(token,login);
        login.setToken(token);
        loginMapper.updateById(login);


        return token;
    }



    private String generateToken(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
