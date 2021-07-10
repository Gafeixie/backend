package com.example.mapper;

import com.example.model.form.Messages;
import org.apache.ibatis.annotations.*;

/**
 * @author: 谢佳辉
 * @date 2021/4/30 10:57 上午
 */
@Mapper
public interface ExamineMapper {

    @Update("update evidence set examine=#{departId} ,staues= #{staues} where evidence_Id =#{evidenceId}")
    public void passExam(String departId,String evidenceId,String staues);

    @Select("select staff_Name from staff where staff_id = #{staffId}")
    public String findname(String staffId);

    @Select("select department_id from staff where staff_id = #{staffId}")
    public String findDepartment(String staffId);

    @Select("select staues from evidence where evidence_id = #{evidenceId}")
    public String findPassDepart(String evidenceId);

}
