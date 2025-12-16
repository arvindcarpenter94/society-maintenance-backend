package com.society.maintenance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.society.maintenance.entity.Flat;

public interface FlatRepository extends JpaRepository<Flat, Long> {
	
	boolean existsByFlatNumber(String flatNumber);

    List<Flat> findAllByOrderByFlatNumberAsc();
    
    @Query("SELECT f FROM Flat f ORDER BY CAST(f.flatNumber AS integer)")
    List<Flat> findAllSortedNumerically();
    
    Optional<Flat> findByFlatNumber(String flatNumber);
}
