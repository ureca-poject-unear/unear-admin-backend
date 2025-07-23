package com.unear.admin.places.repository;

import com.unear.admin.places.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query(value = """
    SELECT p.* FROM places p
    JOIN unear_events e ON e.unear_event_id = :eventId
    WHERE ST_DistanceSphere(
        ST_MakePoint(p.longitude, p.latitude),
        ST_MakePoint(e.longitude, e.latitude)
    ) <= e.radius_meter
    AND p.place_id NOT IN (
        SELECT ep.place_id FROM event_places ep WHERE ep.unear_event_id = :eventId
    )
""", nativeQuery = true)
    List<Place> findPartnersWithinEventRadius(@Param("eventId") Long eventId);

    @Query(value = """
    SELECT * FROM places p
    WHERE ST_DistanceSphere(
        ST_MakePoint(p.longitude, p.latitude),
        ST_MakePoint(:lng, :lat)
    ) <= :radius
""", nativeQuery = true)
    List<Place> findWithinRadius(
            @Param("lat") BigDecimal lat,
            @Param("lng") BigDecimal lng,
            @Param("radius") Integer radius
    );

}
