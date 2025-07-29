package com.unear.admin.event.repository;

import com.unear.admin.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * 현재 활성화된 이벤트 (isActive = true)
     */
    Optional<Event> findByIsActiveTrue();

    /**
     * 모든 이벤트 isActive = false 로 초기화
     */
    @Modifying
    @Query("UPDATE Event e SET e.isActive = false")
    void deactivateAllEvents();

    /**
     * 오늘 날짜에 해당하는 이벤트 1개 조회 (isActive = false 인 것)
     */
    @Query("""
        SELECT e FROM Event e
        WHERE e.startAt <= :today AND e.endAt >= :today
          AND e.isActive = false
        ORDER BY e.startAt ASC
    """)
    Optional<Event> findNextEventToActivate(@Param("today") LocalDate today);
}
