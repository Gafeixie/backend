package com.example.service.impl;

import com.Result;
import com.example.model.form.MessageForm;
import com.example.model.form.Messages;
import com.example.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 谢佳辉
 * @date 2021/4/30 3:38 下午
 */
@Service
public class RedisServerImpl {
    @Autowired
    RedisUtil redisUtil;
    public Result getMessage(MessageForm messageForm){

        Result result = new Result();
        try{
            List<Object> list =redisUtil.range(messageForm.getKeys(),(long)messageForm.getStart(),(long)messageForm.getEnd());
            result.setData(list);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.error("错误查询");
        }


        return  result;
    }

    public Result addMessage( Messages message){
        try {
            redisUtil.leftPush(message.getKey(),message);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.error("发送失败");
        }

        return Result.ok("发送成功");
    }
}
