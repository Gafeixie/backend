package com.example.model.vo;

import lombok.Data;

/**
 * @author: 谢佳辉
 * @date 2021/1/12 8:19 下午
 */
@Data
public class ReturnCase {
    public String caseId;
    public String caseName;
    public String  caseTypeId;
    public String caseDecription;
    public int  importace;
    public String staffId;
    public String time;
}
