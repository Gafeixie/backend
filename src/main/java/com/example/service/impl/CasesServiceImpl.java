package com.example.service.impl;

import com.example.mapper.*;
import com.example.model.CaseStatus;
import com.example.model.Cases;
import com.example.model.form.AddCreateForm;
import com.example.model.form.CaseForm;
import com.example.service.ICasesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.service.IEvidenceService;
import com.example.util.DepartmentId;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.model.Login;
import com.example.model.Staff;
import com.example.model.vo.ReturnCase;
import com.example.rmso.ResultVO;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-01-12
 */
@Service
public class CasesServiceImpl extends ServiceImpl<CasesMapper, Cases> implements ICasesService {
    @Autowired
    ExamineMapper examineMapper;
    @Autowired
    CasesMapper caseMapper;
    @Autowired
    StaffMapper staffMapper;
    @Autowired
    CaseTypeMapper caseTypeMapper;
    @Autowired
    CaseStatusMapper caseStatusMapper;
    @Override
    public ResultVO findCase(Login login) {
        Staff staff = staffMapper.selectOne(new QueryWrapper<Staff>().eq("Staff_id",login.getStaffId()));
        Integer departmentId = staff.getDepartmentId();
        QueryWrapper queryWrapper = new QueryWrapper<Cases>().like("Department_ids","101");
        List<Cases> cases = caseMapper.selectList(queryWrapper);


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
    @Transactional
    public ResultVO findCaseByType(CaseForm caseForm, Login cLogin) {
        String departmrntId = examineMapper.findDepartment(cLogin.getStaffId());
        List<ReturnCase> returnCase = caseTypeMapper.findCaseByType(departmrntId,caseForm.getNote(),caseForm.getImportances(),caseForm.getStaffId(),caseForm.getType());
        return new ResultVO(returnCase);
    }

    @Override
    public ResultVO createCases(AddCreateForm addCreateForm) {
        String ids = "";
        Cases cases = new Cases();
        Subject subject = SecurityUtils.getSubject();
        Login currentlogin =(Login)subject.getPrincipal();

        cases.setCaseDescription(addCreateForm.getCaseDescription());
        cases.setCaseId(addCreateForm.getCaseId());
        cases.setCaseName(addCreateForm.getCaseName());
        for (String id : addCreateForm.getIds()) {
            ids = ids+id+";";
        }
        cases.setDepartmentIds(ids);
        cases.setCaseTypeId(addCreateForm.getCaseType());
        cases.setStaffId(currentlogin.getStaffId());
        cases.setImportace(addCreateForm.getImportances());
        caseMapper.insert(cases);
        return ResultVO.ok();
    }
    public ResultVO updateCase(String CaseId){

        Cases cases=   caseMapper.selectOne(new QueryWrapper<Cases>().eq("Case_id",CaseId));
        cases.setImportace(cases.getImportace()+1);
        caseMapper.updateById(cases);
        return  new ResultVO("审核通过");
    }
}
