package com.los.repository;

import com.los.entity.MasterMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MasterMenuRepository extends JpaRepository<MasterMenu, Long> {
}
