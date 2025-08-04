package com.unear.admin.summary.service.impl;

import com.unear.admin.summary.dto.response.EventCompletionSummaryDto;
import com.unear.admin.summary.dto.response.KeywordSummaryResponseDto;
import com.unear.admin.summary.repository.UserActionSummaryRepository;
import com.unear.admin.summary.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;


@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final UserActionSummaryRepository summaryRepository;

    @Override
    public List<KeywordSummaryResponseDto> getTopSummaryByActionType(
            String ageGroup,
            String gender,
            String yearMonth,
            String actionType
    ) {
        if (yearMonth == null) {
            throw new IllegalArgumentException("yearMonth는 필수입니다.");
        }

        String group = (ageGroup != null && gender != null) ? ageGroup + "_" + gender : null;

        YearMonth ym = YearMonth.parse(yearMonth); // 예: "2025-08"
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();

        return summaryRepository.findTop10KeywordsByMonth(actionType, group, startDate, endDate);
    }

    @Override
    public List<KeywordSummaryResponseDto> getTopSummaryByGroupField(
            String actionType, String group, LocalDate startDate, LocalDate endDate) {
        return summaryRepository.findTop10ByGroupByField(
                actionType, group, startDate, endDate
        );
    }

    @Override
    public List<EventCompletionSummaryDto> getEventCompletionSummary(String ageGroup, String gender, String yearMonth) {
        String groupKey = (ageGroup != null && gender != null) ? ageGroup + "_" + gender : null;

        LocalDate startDate = LocalDate.parse(yearMonth + "-01");
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Object[]> rawData = summaryRepository.findEventStatsGrouped(groupKey, startDate, endDate);

        Map<String, Map<String, Long>> grouped = new HashMap<>();

        for (Object[] row : rawData) {
            String group = (String) row[0];
            String type = (String) row[1];
            Long count = (Long) row[2];

            grouped
                    .computeIfAbsent(group, k -> new HashMap<>())
                    .put(type, count);
        }

        List<EventCompletionSummaryDto> result = grouped.entrySet().stream()
                .map(entry -> {
                    String group = entry.getKey();
                    Map<String, Long> counts = entry.getValue();

                    long join = counts.getOrDefault("EVENT_JOIN", 0L);
                    long done = counts.getOrDefault("EVENT_DONE", 0L);
                    long drop = counts.getOrDefault("EVENT_DROP", 0L);

                    double completionRate = join > 0 ? (double) done / join : 0;
                    double dropRate = join > 0 ? (double) drop / join : 0;

                    return new EventCompletionSummaryDto(group, join, done, drop, completionRate, dropRate);
                })
                .sorted(Comparator.comparing(EventCompletionSummaryDto::getJoinCount).reversed())
                .collect(Collectors.toList());

        if (ageGroup == null && gender == null) {
            long totalJoin = 0, totalDone = 0, totalDrop = 0;
            for (EventCompletionSummaryDto dto : result) {
                totalJoin += dto.getJoinCount();
                totalDone += dto.getDoneCount();
                totalDrop += dto.getDropCount();
            }

            double totalCompletionRate = totalJoin > 0 ? (double) totalDone / totalJoin : 0;
            double totalDropRate = totalJoin > 0 ? (double) totalDrop / totalJoin : 0;

            EventCompletionSummaryDto totalDto = new EventCompletionSummaryDto(
                    "ALL", totalJoin, totalDone, totalDrop, totalCompletionRate, totalDropRate
            );

            result.add(0, totalDto);
        }

        return result;
    }






}