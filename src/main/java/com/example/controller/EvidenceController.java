package com.example.controller;


import com.example.model.Login;
import com.example.model.form.EvidenceForm;
import com.example.model.form.PostEvidence;
import com.example.rmso.ResultVO;
import com.example.service.IEvidenceService;
import com.example.service.TokenService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.InputStream;

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
@RequestMapping("/evidence")
public class EvidenceController {

    @Autowired
    TokenService tokenService;
    @Autowired
    IEvidenceService iEvidenceService;


    @PostMapping("getevidence")
    public ResultVO getEvidence(@RequestBody EvidenceForm evidenceForm) throws InterruptedException {
        Subject subject = SecurityUtils.getSubject();
        Login currentlogin =(Login)subject.getPrincipal();

        return iEvidenceService.getEvidence(currentlogin,evidenceForm.caseId);
    }
    @PostMapping("postevidence")

    public ResultVO addEvidence(  PostEvidence postEvidence) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        Login currentlogin =(Login)subject.getPrincipal();
        System.out.println("==="+postEvidence.getEvidenceUrl());
        iEvidenceService.addEvidence(postEvidence,currentlogin.getStaffId());
        return ResultVO.ok();
    }
    @GetMapping("getone/{evidenceId}")
    public ResultVO getOne(@PathVariable Integer evidenceId) throws Exception {
        return iEvidenceService.getOneEvidence(evidenceId);
    }
    @ApiOperation("审核证据")
    @GetMapping("get/{CaseId}")
    public ResultVO getUnExam(@PathVariable String CaseId){
        Login currentlogin;
        try{
            Subject subject = SecurityUtils.getSubject();
             currentlogin =(Login)subject.getPrincipal();

        }catch (RuntimeException e){
            e.printStackTrace();
            return  ResultVO.error("未登录");
        }
        return  iEvidenceService.getUnEvidence(currentlogin,CaseId);
    }
}