package com.example.service.impl;

import com.example.mapper.CasesMapper;
import com.example.mapper.ExamineMapper;
import com.example.model.Cases;
import com.example.model.vo.ReturnCase;
import com.example.rmso.ResultVO;
import com.example.service.IICaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class IICaseServiceImpl implements IICaseService {
    @Autowired
    ExamineMapper examineMapper;
    @Autowired
    CasesMapper caseMapper;
    @Override
    public Cases queryCaseById(String caseId)
    {
        Cases cases=caseMapper.queryCaseById(caseId);
        return  cases;
    }
    @Override
    public ResultVO findCase() {
        List<Cases> cases = caseMapper.findCase();
        List<ReturnCase> returnCases = new ArrayList<>();

        for(Cases a :cases){
            String as=null;

            ReturnCase rc = new ReturnCase();
            rc.setCaseDecription(a.getCaseDescription());
            rc.setCaseId(a.getCaseId());
            rc.setCaseName(a.getCaseName());

            rc.setCaseTypeId(a.getCaseTypeId());
            rc.setStaffId(examineMapper.findname(a.getStaffId()));
            rc.setTime(a.getCreateDate().toString());
            rc.setImportace(a.getImportace());
            returnCases.add(rc);
        }
        returnCases.sort((t1, t2) -> t2.getTime().compareTo(t1.getTime()));
        return new ResultVO(returnCases);
    }
    @Override
    public ResultVO findCaseByContdition(Cases cases)
    {
        List<Cases> casess = caseMapper.findCaseByContdition(cases);
        List<ReturnCase> returnCases = new ArrayList<>();

        for(Cases a :casess){
            String as=null;

            ReturnCase rc = new ReturnCase();
            rc.setCaseDecription(a.getCaseDescription());
            rc.setCaseId(a.getCaseId());
            rc.setCaseName(a.getCaseName());

            rc.setCaseTypeId(a.getCaseTypeId());
            rc.setStaffId(examineMapper.findname(a.getStaffId()));
            rc.setTime(a.getCreateDate().toString());
            rc.setImportace(a.getImportace());
            returnCases.add(rc);
        }
        returnCases.sort((t1, t2) -> t2.getTime().compareTo(t1.getTime()));
        return new ResultVO(returnCases);
    }

    @Override
    public ResultVO newCreateCases(Cases cases) {

        caseMapper.newCreateCases(cases);
        ResultVO resultVO=new ResultVO(1);
        return resultVO;
    }



    @Override
    public ResultVO newUpdateCase(Cases cases) {
        caseMapper.newUpdateCase(cases);
        ResultVO resultVO=new ResultVO(1);
        return resultVO;
    }
}
