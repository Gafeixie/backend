package com.example.controller;


import com.example.model.form.LoginForm;
import com.example.rmso.ResultCode;
import com.example.rmso.ResultVO;
import com.example.service.Login;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-01-12
 */
@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    Login loginservice;
    @PostMapping("logins")
    public ResultVO  login(@RequestBody LoginForm loginForm){
        if(loginForm.getStaffId().equals(" ")||loginForm.getStaffId()==null){
            return ResultVO.error("请输入正确的员工号");
        }
        if(loginForm.getPassword().equals(" ")||loginForm.getPassword()==null){
            return  ResultVO.error("请输入正确的密码");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginForm.staffId,loginForm.password);
        try{
            subject.login(token);//执行登录
        }catch (UnknownAccountException e){
            return new ResultVO(ResultCode.FAILED,"用户名错误");
        }catch (IncorrectCredentialsException e){
            return  new ResultVO(ResultCode.FAILED,"密码错误");
        }

        return  new ResultVO(loginservice.login()) ;

    }
    @GetMapping("noauth")
    public ResultVO unauthorized(){
        return new ResultVO("未授权无法访问");
    }
    @GetMapping("logout")
    public ResultVO logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultVO.error("没有权限");
    }
    @PostMapping("ulogin")
    public ResultVO ulogin(){
        return ResultVO.error("请先登录");
    }



}
