package com.example.service;

import com.example.model.Cases;
import com.example.rmso.ResultVO;

import java.util.List;

public interface IICaseService{
    public ResultVO findCase();
    public ResultVO findCaseByContdition(Cases cases);
    public ResultVO newCreateCases(Cases cases);
    public ResultVO  newUpdateCase(Cases cases);
    public Cases queryCaseById(String caseId);
}
