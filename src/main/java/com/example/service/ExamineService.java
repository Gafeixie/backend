package com.example.service;

import com.Result;
import com.example.model.Login;
import com.example.model.form.ExamineForm;
import com.example.model.form.PostEvidence;
import com.example.rmso.ResultVO;

/**
 * @author: 谢佳辉
 * @date 2021/4/30 10:47 上午
 */
public interface ExamineService {
    public Result passExamine(Login currentlogin, String evidenceId);
    public Result RefuseExamine(Login currentlogin, ExamineForm examineForm);
}
