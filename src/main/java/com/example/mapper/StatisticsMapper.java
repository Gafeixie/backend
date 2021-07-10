package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author: 谢佳辉
 * @date 2021/5/4 3:18 上午
 */
@Mapper
public interface StatisticsMapper {
    @Select({
           " select count(case_id) from cases "
    })
    public int CaseNumber();
    @Select({
            " select count(Evidence_id) from evidence "
    })
    public int evidenceNumber();

}
