package com.unear.admin.summary.service;

import com.unear.admin.summary.dto.response.KeywordSummaryResponseDto;

import java.util.List;

public interface SummaryService {
    List<KeywordSummaryResponseDto> getTop10KeywordsByAgeGender(String ageGroup, String gender, String actionType);
}