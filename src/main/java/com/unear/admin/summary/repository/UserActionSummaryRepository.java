package com.unear.admin.summary.repository;

import com.unear.admin.summary.dto.response.KeywordSummaryResponseDto;
import com.unear.admin.summary.entity.UserActionSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface UserActionSummaryRepository extends JpaRepository<UserActionSummary, Long> {

    @Query("""
        SELECT new com.unear.admin.summary.dto.response.KeywordSummaryResponseDto(
            s.groupByText, s.count, s.distinctUserCount
        )
        FROM UserActionSummary s
        WHERE s.actionType = :actionType
          AND s.groupByField = :group
        ORDER BY s.count DESC
        LIMIT 10
    """)
    List<KeywordSummaryResponseDto> findTop10KeywordsByGroup(
            @Param("actionType") String actionType,
            @Param("group") String group
    );
}
