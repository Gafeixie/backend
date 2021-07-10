package com.example.controller;

import com.Result;
import com.example.model.Evidence;
import com.example.model.Login;
import com.example.model.form.EvidenceForm;
import com.example.model.form.ExamineForm;
import com.example.service.ExamineService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 谢佳辉
 * @date 2021/4/30 10:23 上午
 */
@CrossOrigin
@RestController
@RequestMapping("/examine")
public class ExamineController {
    @Autowired
    ExamineService examineService;
    @CrossOrigin
    @PutMapping("passEXamine/{evidenceId}")
    public Result passExamine( @PathVariable String evidenceId){
        Login currentlogin;
        try{
            Subject subject = SecurityUtils.getSubject();
           currentlogin =(Login)subject.getPrincipal();
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.error("请先登录");
        }

      return   examineService.passExamine(currentlogin,evidenceId);
    }
    @PutMapping("RefuseExamine")
    public Result changeExamine(@RequestBody ExamineForm examineForm){
        Login currentlogin;
        try{
            Subject subject = SecurityUtils.getSubject();
            currentlogin =(Login)subject.getPrincipal();
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.error("请先登录");
        }
        return  examineService.RefuseExamine(currentlogin,examineForm);
    }

}
