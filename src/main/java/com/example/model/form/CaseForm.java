package com.example.model.form;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @author: 谢佳辉
 * @date 2021/1/26 7:22 下午
 */
@Data
public class CaseForm {
    @ApiParam(name = "案件描述")
    String note;
    @ApiParam(name = "办案人")
    String staffId;
    @ApiParam(name="案件类型")
    String type;
    @ApiParam(name="重要程度")
    int  importances;
}
