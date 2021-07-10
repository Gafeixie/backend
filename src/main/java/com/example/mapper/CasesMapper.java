package com.example.mapper;

import com.example.model.Cases;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-01-12
 */

@Repository
@Mapper
public interface CasesMapper extends BaseMapper<Cases> {
    public List<Cases> findCase();
    public List<Cases> findCaseByContdition(Cases cases);
    public int  newCreateCases(Cases cases);
    public int  newUpdateCase(Cases cases);
    public Cases queryCaseById(String caseId);
}
