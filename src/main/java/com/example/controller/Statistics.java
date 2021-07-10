package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.CasesMapper;
import com.example.mapper.StaffMapper;
import com.example.mapper.StatisticsMapper;
import com.example.model.Cases;
import com.example.model.vo.StatisticsVo;
import com.example.rmso.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: 谢佳辉
 * @date 2021/5/4 3:17 上午
 */
@CrossOrigin
@RestController
@RequestMapping("/statistics")
@Api(value = "统计")
public class Statistics {
    @Autowired
    StatisticsMapper statisticsMapper;
    @Autowired
    CasesMapper casesMapper;
    @GetMapping("pick")
    public ResultVO statis(){
        StatisticsVo statisticsVo = new StatisticsVo();
        statisticsVo.setCasesNumber(statisticsMapper.CaseNumber());
        statisticsVo.setEvidenceNumber(statisticsMapper.evidenceNumber());
        String n = "刑事案件";
        String n1 = "行政案件";
        QueryWrapper queryWrapper =new QueryWrapper<>().eq("Case_type_id",n);
       List<Cases> list =  casesMapper.selectList(queryWrapper);
        QueryWrapper queryWrapper1 =new QueryWrapper<>().eq("Case_type_id",n1);

        List<Cases> list1 =  casesMapper.selectList(queryWrapper1);
        statisticsVo.setCasesNumberBy1(list.size());
        statisticsVo.setCasesNumberBy0(list1.size());
        return new  ResultVO(statisticsVo);
    }
}
