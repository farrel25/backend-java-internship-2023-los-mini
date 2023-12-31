package com.los.repository;

import com.los.entity.MasterUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MasterUserRepository extends JpaRepository<MasterUser, Long> {

    Optional<MasterUser> findByUsername(String username);

    boolean existsByUsername(String username);
}
