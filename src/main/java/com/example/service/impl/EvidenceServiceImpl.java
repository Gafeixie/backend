package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bcos.asset.client.EDVclient;
import com.example.config.BcosConfig;
import com.example.mapper.ExamineMapper;
import com.example.mapper.StaffMapper;
import com.example.model.Cases;
import com.example.model.Evidence;
import com.example.mapper.EvidenceMapper;
import com.example.model.Login;
import com.example.model.Staff;
import com.example.model.form.PostEvidence;
import com.example.model.vo.ReturnEvidenceList;
import com.example.rmso.ResultVO;
import com.example.service.IEvidenceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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
public class EvidenceServiceImpl extends ServiceImpl<EvidenceMapper, Evidence> implements IEvidenceService {
    @Autowired
    BcosConfig bcosConfig;
    @Autowired
    StaffMapper staffMapper;
    @Autowired
    EvidenceMapper evidenceMapper;
    @Autowired
    ExamineMapper examineMapper;
    @Autowired
    EDVclient edVclient;
    @Autowired
    private  RestTemplate restTemplate;




    @Override
    public ResultVO getEvidence(Login login,String caseId) {
        System.out.println(Thread.currentThread().getName()+"serxxxxxxxxxxx");
        Staff staff = staffMapper.selectOne(new QueryWrapper<Staff>().eq("Staff_id",login.getStaffId()));
        Integer departmentId = staff.getDepartmentId();
        QueryWrapper queryWrapper = new QueryWrapper<Cases>().eq("Case_id",caseId).like("Department_ids","101");
        List<Evidence> evidences =evidenceMapper.selectList(queryWrapper);
        List<ReturnEvidenceList> lists = new ArrayList<>();
        for (Evidence evidence : evidences) {
            ReturnEvidenceList returnEvidenceList = new ReturnEvidenceList();
            returnEvidenceList.setEvidenceId(evidence.getEvidenceId().toString());
            returnEvidenceList.setNote(evidence.getNote());
            returnEvidenceList.setDate(evidence.getCreateDate().toString());
            returnEvidenceList.setExamine(evidence.getExamine());
            returnEvidenceList.setUrl(evidence.getEvidenceUrl());
            lists.add(returnEvidenceList);


        }
        lists.sort((t1, t2) -> t2.getDate().compareTo(t1.getDate()));
        return  new ResultVO(lists);
    }

    @Override
    @Transactional
    public ResultVO addEvidence(PostEvidence postEvidence, String staffId) throws Exception {
        String ids = new String();
        for (String department_id : postEvidence.getDepartmentIds()) {
            ids = ids+department_id+",";
        }

        Evidence evidence = new Evidence();
        evidence.setCaseId(postEvidence.getCaseId());
        evidence.setDepartmentIds(ids);
        evidence.setNote(postEvidence.getNote());
        evidence.setStaffId(staffId);
        evidence.setEvidenceUrl(postEvidence.getEvidenceUrl());
        evidence.setEvidenceId(new Integer(postEvidence.getEvidenceId()));
        evidence.setIsImg(postEvidence.getIsImg());
        evidence.setStaues(Integer.valueOf(examineMapper.findDepartment(staffId)));
        evidence.setBlockHash(postEvidence.hash);
        evidenceMapper.insert(evidence);
        try{
           edVclient.put(evidence,postEvidence.hash);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.error("区块链错误");
        }
        return  ResultVO.ok();
    }
    @Transactional
    public ResultVO getOneEvidence(Integer Evidence_id) throws Exception {
        Evidence evidence =evidenceMapper.selectOne(new QueryWrapper<Evidence>().eq("Evidence_id",Evidence_id));
        if(evidence.getEvidenceUrl()==null){
            return  ResultVO.error("无可用图片");
        }
        String url = evidence.getEvidenceUrl();
        String hash = checkNuber(url);
        System.out.println(hash);

        List<String> list =edVclient.queryEDV(evidence.getEvidenceId().toString());
        evidence.setBlockHash(list.get(2));
        if(!hash.equals(list.get(2))){
            return ResultVO.error("图片错误");
        }
        if (!evidence.getNote().equals(list.get(3))){
            return ResultVO.error("描述错误");
        }
        return new ResultVO(evidence);
    }

    @Override
    public ResultVO getUnEvidence(Login login, String CaseId) {
        System.out.println(Thread.currentThread().getName()+"serxxxxxxxxxxx");
        Staff staff = staffMapper.selectOne(new QueryWrapper<Staff>().eq("Staff_id",login.getStaffId()));
        Integer departmentId = staff.getDepartmentId();
        String n = String.valueOf(departmentId);
        QueryWrapper queryWrapper = new QueryWrapper<Cases>().eq("staues",departmentId-1).eq("Case_id",CaseId).like("Department_ids",n);
        List<Evidence> evidences =evidenceMapper.selectList(queryWrapper);
        List<ReturnEvidenceList> lists = new ArrayList<>();
        for (Evidence evidence : evidences) {
            ReturnEvidenceList returnEvidenceList = new ReturnEvidenceList();
            returnEvidenceList.setEvidenceId(evidence.getEvidenceId().toString());
            returnEvidenceList.setNote(evidence.getNote());
            returnEvidenceList.setDate(evidence.getCreateDate().toString());
            returnEvidenceList.setExamine(evidence.getExamine());
            lists.add(returnEvidenceList);

        }
        return  new ResultVO(lists);
    }


    public String checkNuber(String url){
        String path = String.format("http://120.24.64.8:8082/getHash?path=%s",url);
       String numbers = restTemplate.postForObject(path,Object.class,String.class);
        return numbers;
    }
}