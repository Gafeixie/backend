package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.LoginMapper;
import com.example.mapper.StaffMapper;
import com.example.model.Staff;
import com.example.model.form.LoginForm;
import com.example.model.vo.ReturnLogin;
import com.example.rmso.ResultVO;
import com.example.service.Login;
import com.example.service.TokenService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 谢佳辉
 * @date 2021/1/12 2:32 下午
 */
@Service
public class LoginImpl implements Login {
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    TokenService tokenService;
    @Autowired
    StaffMapper staffMapper;
    @Override
    public ResultVO login() {
        Subject subject = SecurityUtils.getSubject();
        com.example.model.Login currentlogin =(com.example.model.Login)subject.getPrincipal();
        String staffId = currentlogin.getStaffId();
        Staff staff =staffMapper.selectOne(new QueryWrapper<Staff>().eq("Staff_id",staffId));
        ReturnLogin returnLogin  = new ReturnLogin();
        returnLogin.setStaffName(staff.getStaffName());
        returnLogin.setId(staffId);
        returnLogin.setDepartmentId(staff.getDepartmentId());
        ResultVO resultVO = new ResultVO(returnLogin);

        return  resultVO;
    }
}
