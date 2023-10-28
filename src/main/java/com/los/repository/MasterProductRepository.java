package com.los.repository;

import com.los.entity.MasterProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MasterProductRepository extends JpaRepository<MasterProduct, Long> {
    Optional<MasterProduct> findByIdAndIsDeletedFalse(Long id);
}
