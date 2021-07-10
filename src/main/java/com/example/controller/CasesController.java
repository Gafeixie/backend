package com.example.controller;


import com.example.model.Login;
import com.example.model.form.AddCreateForm;
import com.example.model.form.CaseForm;
import com.example.model.form.updateCase;
import com.example.model.vo.tokenVo;
import com.example.service.ICasesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.rmso.ResultVO;

import com.example.service.TokenService;
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
@RequestMapping("/cases")
@Api(value = "案件")
public class CasesController {
    @Autowired
    TokenService tokenService;
    @Autowired
    ICasesService iCaseService;
    @ApiOperation(value = "获取案件")
    @PostMapping ("getcase")
    public ResultVO getcase(){
        Subject subject = SecurityUtils.getSubject();
        Login currentlogin =(Login)subject.getPrincipal();
        return iCaseService.findCase(currentlogin);
    }
    @ApiOperation(value = "按类型获取案件")
    @PostMapping("getTypeCase")
    public ResultVO getTypeCase(@RequestBody CaseForm caseForm){
        Login currentlogin;
        try{
            Subject subject = SecurityUtils.getSubject();
            currentlogin =(Login)subject.getPrincipal();

        }catch (RuntimeException e){
            e.printStackTrace();
            return  ResultVO.error("未登录");
        }
        return new ResultVO(iCaseService.findCaseByType(caseForm,currentlogin));

    }
    @ApiOperation(value = "创建案件")
    @PostMapping("addCase")
    public ResultVO addCase(@RequestBody AddCreateForm addCreateForm){
        iCaseService.createCases(addCreateForm);
        return ResultVO.ok();
    }
    @ApiOperation(value = "审核")
    @PostMapping("updateCase")
    public ResultVO updateCase(@RequestBody updateCase updateCase){

        return iCaseService.updateCase(updateCase.getCaseId());
    }
}
