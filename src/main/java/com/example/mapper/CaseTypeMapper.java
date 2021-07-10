package com.example.mapper;

import com.example.model.vo.ReturnCase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 谢佳辉
 * @date 2021/5/3 10:24 下午
 */
@Mapper
public interface CaseTypeMapper {
    @Select({"<script> select case_id,case_name,Case_type_id,Case_description,importace,Staff_id,from_department from cases where Department_ids like #{departmentId}" +
            "<if test='importances != null '>AND importace=#{importances}</if>" +
            "<if test='staffId != null'>AND staff_Id = #{staffId}</if>" +
            "<if test ='type!= null '>   AND Case_type_id = #{type} </if>" +
            "<if test='note != null'>AND Case_description like #{note}</if></script>"
            }
      )
    public List<ReturnCase> findCaseByType(String departmentId, String note, int importances, String staffId, String type);


}


