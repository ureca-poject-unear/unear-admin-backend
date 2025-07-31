package com.unear.admin.summary.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_action_summary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserActionSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action_type", nullable = false)
    private String actionType;

    @Column(name = "group_by_field", nullable = false)
    private String groupByField;

    @Column(name = "group_by_text", nullable = false)
    private String groupByText;

    @Column(name = "distinct_user_count", nullable = false)
    private int distinctUserCount;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "summary_date", nullable = false)
    private LocalDate summaryDate;
}