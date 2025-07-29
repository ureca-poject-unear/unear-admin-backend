package com.unear.admin.eventplace.repository;

import com.unear.admin.eventplace.entity.EventPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EventPlaceRepository extends JpaRepository<EventPlace, Long> {

    @Modifying
    @Query(value = """
    DELETE FROM event_places
    WHERE event_place_id IN (
        SELECT ep.event_place_id
        FROM event_places ep
        JOIN unear_events e ON ep.unear_event_id = e.unear_event_id
        WHERE ep.event_code = 'REQUIRE'
          AND e.end_at < :today
    )
""", nativeQuery = true)
    void deleteExpiredPopupEventPlaces(@Param("today") LocalDate today);


    @Modifying
    @Query("""
    DELETE FROM EventPlace ep
    WHERE ep.event.endAt < :today
      AND ep.eventCode = 'GENERAL'
""")
    void deleteExpiredGeneralEventPlaces(@Param("today") LocalDate today);

}
