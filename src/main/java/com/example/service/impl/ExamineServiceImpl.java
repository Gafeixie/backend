package com.example.service.impl;

import com.Result;
import com.example.mapper.ExamineMapper;
import com.example.model.Login;
import com.example.model.form.ExamineForm;
import com.example.model.form.MessageForm;
import com.example.model.form.Messages;
import com.example.service.ExamineService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 谢佳辉
 * @date 2021/4/30 10:47 上午
 */
@Service
public class ExamineServiceImpl implements ExamineService {
    @Autowired
    ExamineMapper examineMapper;
    @Autowired
    RedisServerImpl redisServer;
    @Override
    @Transactional
    public Result passExamine(Login currentlogin, String evidenceId) {
        String departmentId;
        Messages messages =  new Messages();

        Map<String,String> map =new HashMap<>();
        map.put("101","公安部门");
        map.put("102","检察院");
        map.put("103","法院");
        map.put("104","司法局");
        String key ;
        try {
           String passDepart= examineMapper.findPassDepart(evidenceId);
            departmentId= examineMapper.findDepartment(currentlogin.getStaffId());
           String value =  map.get(departmentId)+"审核通过";
            messages.setKey(examineMapper.findPassDepart(evidenceId));
            messages.setName(examineMapper.findname(currentlogin.getStaffId()));
            messages.setEvidencId(evidenceId);
            messages.setValue(value);
            examineMapper.passExam(value,evidenceId,departmentId);
        }catch (Exception e){

            return Result.error("未登录");
        }

        redisServer.addMessage(messages);
        return Result.ok("更改成功");

    }

    @Transactional
    public Result RefuseExamine(Login currentlogin, ExamineForm examineForm) {
        String departmentId;
        Messages messages =  new Messages();

        Map<String,String> map =new HashMap<>();
        map.put("101","公安部门");
        map.put("102","检察院");
        map.put("103","法院");
        map.put("104","司法局");

        String key ;
        try {
            String passDepart= examineMapper.findPassDepart(examineForm.getEvidenceId());
            departmentId= examineMapper.findDepartment(currentlogin.getStaffId());
            String value =  map.get(departmentId)+"审核不通过";
            messages.setKey(examineMapper.findPassDepart(examineForm.getEvidenceId()));
            messages.setName(examineMapper.findname(currentlogin.getStaffId()));
            messages.setEvidencId(examineForm.getEvidenceId());
            messages.setValue(examineForm.getValue());
            examineMapper.passExam(value,examineForm.getEvidenceId(),passDepart);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("未登录");
        }

        redisServer.addMessage(messages);
        return Result.ok("更改成功");

    }
}
