package com.example.controller;



import com.Result;
import com.example.mapper.ExamineMapper;
import com.example.model.Login;
import com.example.model.form.MessageForm;
import com.example.model.form.MessageLastForm;
import com.example.model.form.Messages;
import com.example.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 谢佳辉
 * @date 2021/4/28 8:15 下午
 */
@RestController
@RequestMapping(("/message"))
public class MessageController {
    /**
     *  用于获取消息队列中具体消息
     */
    @Autowired
    ExamineMapper examineMapper;
    @Autowired
    RedisUtil redisUtil;
    @PostMapping("getList")
    @CrossOrigin
    public Result getMessage(@RequestBody MessageLastForm messageForm){
        Result result = new Result();
        Login currentlogin;
        try{

            Subject subject = SecurityUtils.getSubject();
            currentlogin =(Login)subject.getPrincipal();
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.error("请先登录");
        }
        String n =examineMapper.findDepartment(currentlogin.getStaffId());
        try{
            List<Object> list =redisUtil.range(n,(long)messageForm.getStart(),(long)messageForm.getEnd());
            result.setData(list);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.error("错误查询");
        }


        return  result;
    }
    @PostMapping("addMessage")
    public Result addMessage(@RequestBody Messages message){
       message.setDate(new Date());
        redisUtil.leftPush(message.getKey(),message);
        return Result.ok("发送成功");
    }

}
