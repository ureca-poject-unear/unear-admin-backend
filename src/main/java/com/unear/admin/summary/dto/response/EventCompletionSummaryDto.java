package com.unear.admin.summary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventCompletionSummaryDto {
    private String group;
    private long joinCount;
    private long doneCount;
    private long dropCount;
    private double completionRate;
    private double dropRate;
}

