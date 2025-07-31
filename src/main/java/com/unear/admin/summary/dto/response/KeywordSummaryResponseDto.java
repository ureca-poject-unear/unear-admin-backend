package com.unear.admin.summary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KeywordSummaryResponseDto {
    private String keyword;
    private int userCount;
    private int totalCount;
}