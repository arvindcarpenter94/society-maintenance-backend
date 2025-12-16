package com.society.maintenance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.maintenance.entity.SocietyRate;

public interface SocietyRateRepository extends JpaRepository<SocietyRate, Long> {
    Optional<SocietyRate> findByEffectiveToIsNull();
}
