package com.los.repository;

import com.los.entity.MasterRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MasterRoleRepository extends JpaRepository<MasterRole, Long> {

    boolean existsByRoleCode(String roleCode);

    Optional<MasterRole> findByRoleCode(String roleCode);
}
