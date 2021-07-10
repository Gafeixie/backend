package com.example.service;

import com.Result;
import com.example.model.Evidence;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.Login;
import com.example.model.form.PostEvidence;
import com.example.rmso.ResultVO;
import org.junit.Before;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-01-12
 */

public interface IEvidenceService extends IService<Evidence> {
    public ResultVO getEvidence(Login login,String CaseId);
    public ResultVO addEvidence(PostEvidence postEvidence,String staffId) throws IOException, Exception;
    public ResultVO getOneEvidence(Integer Evidence_id) throws Exception;
    public ResultVO getUnEvidence(Login login , String CaseId);
}