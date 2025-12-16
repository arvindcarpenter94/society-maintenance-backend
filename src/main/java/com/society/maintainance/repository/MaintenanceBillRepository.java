package com.society.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.maintenance.entity.MaintenanceBill;

public interface MaintenanceBillRepository extends JpaRepository<MaintenanceBill, Long> {
	
	boolean existsByFlat_IdAndMonthYear(Long flatId, String monthYear);
}
