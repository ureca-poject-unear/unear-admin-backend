package com.unear.admin.summary.repository;

import com.unear.admin.summary.dto.response.KeywordSummaryResponseDto;
import com.unear.admin.summary.entity.UserActionSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;

public interface UserActionSummaryRepository extends JpaRepository<UserActionSummary, Long> {

    @Query("""
    SELECT new com.unear.admin.summary.dto.response.KeywordSummaryResponseDto(
        s.groupByText, SUM(s.count), SUM(s.distinctUserCount)
    )
    FROM UserActionSummary s
    WHERE s.actionType = :actionType
      AND (:group IS NULL OR s.groupByField = :group)
      AND s.summaryDate BETWEEN :startDate AND :endDate
    GROUP BY s.groupByText
    ORDER BY SUM(s.count) DESC
""")
    List<KeywordSummaryResponseDto> findTop10KeywordsByMonth(
            @Param("actionType") String actionType,
            @Param("group") String group,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );



    @Query("""
    SELECT new com.unear.admin.summary.dto.response.KeywordSummaryResponseDto(
        s.groupByField, SUM(s.count), SUM(s.distinctUserCount)
    )
    FROM UserActionSummary s
    WHERE s.actionType = :actionType
      AND (:group IS NULL OR s.groupByText = :group)
      AND s.summaryDate BETWEEN :startDate AND :endDate
    GROUP BY s.groupByField
    ORDER BY SUM(s.count) DESC
""")
    List<KeywordSummaryResponseDto> findTop10ByGroupByField(
            @Param("actionType") String actionType,
            @Param("group") String group,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );


    @Query("""
        SELECT s.groupByField, s.actionType, SUM(s.count)
        FROM UserActionSummary s
        WHERE s.actionType IN ('EVENT_JOIN', 'EVENT_DONE', 'EVENT_DROP')
          AND (:group IS NULL OR s.groupByField = :group)
          AND s.summaryDate BETWEEN :startDate AND :endDate
        GROUP BY s.groupByField, s.actionType
    """)
    List<Object[]> findEventStatsGrouped(
            @Param("group") String group,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );




}
