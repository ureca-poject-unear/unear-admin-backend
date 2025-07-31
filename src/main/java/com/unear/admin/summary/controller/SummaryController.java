package com.unear.admin.summary.controller;

import com.unear.admin.summary.dto.response.KeywordSummaryResponseDto;
import com.unear.admin.summary.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/summary")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    @GetMapping("/keywords-benefit")
    public ResponseEntity<List<KeywordSummaryResponseDto>> getTopBenefitKeywordsByAgeGender(
            @RequestParam(required = false) String ageGroup,  // 예: "20s"
            @RequestParam(required = false) String gender   // 예: "M"
    ) {
        return ResponseEntity.ok(summaryService.getTop10KeywordsByAgeGender(
                ageGroup, gender, "AGE_GENDER_KEYWORD_BENEFIT"
        ));
    }

    @GetMapping("/keywords-place")
    public ResponseEntity<List<KeywordSummaryResponseDto>> getTopPlaceKeywordsByAgeGender(
            @RequestParam(required = false) String ageGroup,
            @RequestParam(required = false) String gender
    ) {
        return ResponseEntity.ok(summaryService.getTop10KeywordsByAgeGender(
                ageGroup, gender, "AGE_GENDER_KEYWORD_PLACE"
        ));
    }


}