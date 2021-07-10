package com.example.model.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: 谢佳辉
 * @date 2021/1/13 11:25 上午
 */
@Data
public class PostEvidence {
    public  int evidenceId;
    public String evidenceUrl;
    public String note;
    public String[] departmentIds;
    public  String caseId;
    public String  token;
    public  int isImg;
    public String hash;

}
