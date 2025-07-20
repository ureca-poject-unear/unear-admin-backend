package com.unear.admin.eventplace.repository;

import com.unear.admin.eventplace.entity.EventPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPlaceRepository extends JpaRepository<EventPlace, Long> {

}
