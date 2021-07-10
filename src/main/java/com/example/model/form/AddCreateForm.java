package com.example.model.form;

import lombok.Data;

/**
 * @author: 谢佳辉
 * @date 2021/1/26 8:27 下午
 */
@Data
public class AddCreateForm {
    String caseId;
    String caseName;
    String caseType;
    String caseDescription;
    int  importances;
    String[] ids;

}
