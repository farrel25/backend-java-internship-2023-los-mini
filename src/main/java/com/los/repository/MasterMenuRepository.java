package com.los.repository;

import com.los.entity.MasterMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterMenuRepository extends JpaRepository<MasterMenu, Long> {
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM MasterMenu m WHERE m.flowSequence = :flowSequence")
    Boolean existsByFlowSequence(@Param("flowSequence") Long flowSequence);
}
