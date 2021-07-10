package com.example.service;

import com.example.model.Cases;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.Login;
import com.example.model.form.AddCreateForm;
import com.example.model.form.CaseForm;
import com.example.rmso.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-01-12
 */
public interface ICasesService extends IService<Cases> {
    public ResultVO findCase(Login login);
    public ResultVO findCaseByType(CaseForm caseForm, Login cLogin);
    public ResultVO createCases(AddCreateForm addCreateForm);
    public ResultVO updateCase(String CaseId);
}
