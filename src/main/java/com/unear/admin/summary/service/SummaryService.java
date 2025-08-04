package com.unear.admin.summary.service;

import com.unear.admin.summary.dto.response.EventCompletionSummaryDto;
import com.unear.admin.summary.dto.response.KeywordSummaryResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface SummaryService {
    List<KeywordSummaryResponseDto> getTopSummaryByActionType(
            String ageGroup,
            String gender,
            String yearMonth,
            String actionType
    );

    List<KeywordSummaryResponseDto> getTopSummaryByGroupField(
            String actionType, String group, LocalDate startDate, LocalDate endDate);

    List<EventCompletionSummaryDto> getEventCompletionSummary(String ageGroup, String gender, String yearMonth);

}