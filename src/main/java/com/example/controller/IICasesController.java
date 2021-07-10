package com.example.controller;

import com.Result;
import com.example.model.Cases;
import com.example.model.Login;
import com.example.model.form.AddCreateForm;
import com.example.model.form.ConditionForm;
import com.example.model.form.IIupdateCase;
import com.example.rmso.ResultVO;
import com.example.service.IICaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/test/cases")
@Api(value ="案件")
public class IICasesController {
    @Autowired
    IICaseService iCaseService;
    @ApiOperation(value = "创建案件")
    @PostMapping("/addCase")
    public ResultVO addCase(@RequestBody AddCreateForm addCreateForm){
        Login currentlogin;
        try{
            Subject subject = SecurityUtils.getSubject();
            currentlogin =(Login)subject.getPrincipal();

        }catch (RuntimeException e){
            e.printStackTrace();
            return  ResultVO.error("未登录");
        }
        String ids = "";
        Cases cases = new Cases();
        cases.setCaseDescription(addCreateForm.getCaseDescription());
        cases.setCaseId(addCreateForm.getCaseId());
        cases.setCaseName(addCreateForm.getCaseName());
        LocalDateTime l=LocalDateTime.now();
        cases.setCreateDate(l);
        cases.setUpdateDate(l);
        for (String id : addCreateForm.getIds()) {
            ids = ids+id+";";
        }
        cases.setStaffId("1001");
        cases.setDepartmentIds(ids);
        System.out.println(cases.getStaffId());
        cases.setCaseTypeId(addCreateForm.getCaseType());
        cases.setImportace(1);

        return  new ResultVO(iCaseService.newCreateCases(cases));
    }
    @ApiOperation(value = "更新案件",notes="注意问题点:必须传入的字段为CaseId,根据CadeId更新案件,不需要更新的字段取值为null")
    @PostMapping("/updateCase")
    public ResultVO updateCase(@RequestBody IIupdateCase updateCase){
        Login currentlogin;
        try{
            Subject subject = SecurityUtils.getSubject();
            currentlogin =(Login)subject.getPrincipal();

        }catch (RuntimeException e){
            e.printStackTrace();
            return  ResultVO.error("未登录");
        }
        System.out.println("update对象getCaseDescription"+updateCase.getCaseDescription());
        System.out.println("update对象getCaseName"+updateCase.getCaseName());
        System.out.println("update对象getCaseType"+updateCase.getCaseType());
        Cases cases=new Cases();
        cases.setCaseId(updateCase.getCaseId());
        cases.setCaseName(updateCase.getCaseName());
        cases.setCaseDescription(updateCase.getCaseDescription());
        cases.setCaseTypeId(updateCase.getCaseType());
        System.out.println(cases.getCaseTypeId());
        return iCaseService.newUpdateCase(cases);
    }
    @ApiOperation(value = "根据条件查询案件",notes="注意问题点:必须有五个字段其中的一个，不需要的字段设为null,createTime字段代表返回传入时间之后的所有案件，格式为：yyyy-MM-dd HH:mm:ss")
    @PostMapping("/queryCasesByCondition")
    public ResultVO findCaseByContdition(@RequestBody ConditionForm conditionForm){
        Login currentlogin;
        try{
            Subject subject = SecurityUtils.getSubject();
            currentlogin =(Login)subject.getPrincipal();

        }catch (RuntimeException e){
            e.printStackTrace();
            return  ResultVO.error("未登录");
        }
        System.out.println(conditionForm.toString());

        Cases cases=new Cases();
        cases.setCaseTypeId(conditionForm.getCaseType());
        cases.setCaseDescription(conditionForm.getCaseDescription());
        cases.setCaseId(conditionForm.getCaseId());
        cases.setCaseName(conditionForm.getCaseName());
        if(!"".equals(conditionForm.getCreateTime()))
        {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime ldt = LocalDateTime.parse(conditionForm.getCreateTime(),df);
            cases.setCreateDate(ldt);
        }
        return new ResultVO(iCaseService.findCaseByContdition(cases));
    }
    @ApiOperation(value = "无条件查询")

    @PostMapping("/queryCaseList")
    public ResultVO findCase()
    {
        Login currentlogin;
        try{
            Subject subject = SecurityUtils.getSubject();
            currentlogin =(Login)subject.getPrincipal();

        }catch (RuntimeException e){
            e.printStackTrace();
            return  ResultVO.error("未登录");
        }
        return iCaseService.findCase();
    }
}
