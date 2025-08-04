package com.unear.admin.summary.controller;

import com.unear.admin.summary.dto.response.EventCompletionSummaryDto;
import com.unear.admin.summary.dto.response.KeywordSummaryResponseDto;
import com.unear.admin.summary.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    @GetMapping("/summary")
    public ResponseEntity<List<KeywordSummaryResponseDto>> getTopSummary(
            @RequestParam(required = false) String ageGroup,
            @RequestParam(required = false) String gender,
            @RequestParam String yearMonth,
            @RequestParam String type
    ) {
        String actionType = switch (type.toLowerCase()) {
            case "keyword" -> "AGE_GENDER_KEYWORD";
            case "category" -> "AGE_GENDER_CATEGORY";
            case "activate" -> "AGE_GENDER_ACTIVATE_TIME";
            default -> throw new IllegalArgumentException("지원하지 않는 type: " + type);
        };

        return ResponseEntity.ok(summaryService.getTopSummaryByActionType(
                ageGroup, gender, yearMonth, actionType
        ));
    }

    @GetMapping("/summary/event-place")
    public ResponseEntity<List<KeywordSummaryResponseDto>> getEventPlacePopularity(
            @RequestParam String yearMonth
    ) {
        LocalDate startDate = LocalDate.parse(yearMonth + "-01");
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return ResponseEntity.ok(
                summaryService.getTopSummaryByGroupField("EVENT_PLACE_POPULARITY", null, startDate, endDate)
        );
    }


    @GetMapping("/summary/event-stats")
    public ResponseEntity<List<EventCompletionSummaryDto>> getEventCompletionSummary(
            @RequestParam(required = false) String ageGroup,
            @RequestParam(required = false) String gender,
            @RequestParam String yearMonth
    ) {
        return ResponseEntity.ok(summaryService.getEventCompletionSummary(ageGroup, gender, yearMonth));
    }





}