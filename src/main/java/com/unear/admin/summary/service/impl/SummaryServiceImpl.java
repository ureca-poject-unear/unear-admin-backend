package com.unear.admin.summary.service.impl;

import com.unear.admin.summary.dto.response.KeywordSummaryResponseDto;
import com.unear.admin.summary.entity.UserActionSummary;
import com.unear.admin.summary.repository.UserActionSummaryRepository;
import com.unear.admin.summary.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final UserActionSummaryRepository summaryRepository;

    public List<KeywordSummaryResponseDto> getTop10KeywordsByAgeGender(String ageGroup, String gender, String actionType) {
        String group = ageGroup + "_" + gender;  // 예: "20s_M"
        return summaryRepository.findTop10KeywordsByGroup(actionType, group);
    }


}