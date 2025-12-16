package com.society.maintenance.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.society.maintenance.dto.FlatDueDTO;
import com.society.maintenance.entity.Ledger;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {
	
	@Query("""
	        SELECT l.balance
	        FROM Ledger l
	        WHERE l.flat.id = :flatId
	        ORDER BY l.id DESC
	        LIMIT 1
	    """)
	    BigDecimal findLastBalance(@Param("flatId") Long flatId);

	    boolean existsByFlat_IdAndTxnType(Long flatId, String txnType);
	    
	    List<Ledger> findByFlat_IdOrderByIdAsc(Long flatId);

	    @Query("""
	        SELECT l FROM Ledger l
	        WHERE l.flat.id = :flatId
	          AND l.txnDate BETWEEN :from AND :to
	        ORDER BY l.id ASC
	    """)
	    List<Ledger> findLedgerByDateRange(
	            @Param("flatId") Long flatId,
	            @Param("from") LocalDate from,
	            @Param("to") LocalDate to
	    );
	    
	    @Query("""
	            SELECT COALESCE(SUM(l.balance), 0)
	            FROM Ledger l
	            WHERE l.id IN (
	                SELECT MAX(l2.id)
	                FROM Ledger l2
	                GROUP BY l2.flat.id
	            )
	            AND l.balance > 0
	        """)
	        BigDecimal totalOutstanding();

	        @Query("""
	            SELECT COALESCE(SUM(ABS(l.balance)), 0)
	            FROM Ledger l
	            WHERE l.id IN (
	                SELECT MAX(l2.id)
	                FROM Ledger l2
	                GROUP BY l2.flat.id
	            )
	            AND l.balance < 0
	        """)
	        BigDecimal totalAdvance();

	        @Query("""
	            SELECT COUNT(l)
	            FROM Ledger l
	            WHERE l.id IN (
	                SELECT MAX(l2.id)
	                FROM Ledger l2
	                GROUP BY l2.flat.id
	            )
	            AND l.balance > 0
	        """)
	        Long flatsWithDue();
	        
	        @Query("""
	        	    SELECT new com.society.maintenance.dto.FlatDueDTO(
	        	        f.id,
	        	        f.flatNumber,
	        	        f.ownerName,
	        	        l.balance
	        	    )
	        	    FROM Ledger l
	        	    JOIN l.flat f
	        	    WHERE l.id IN (
	        	        SELECT MAX(l2.id)
	        	        FROM Ledger l2
	        	        GROUP BY l2.flat.id
	        	    )
	        	    ORDER BY CAST(f.flatNumber AS integer)
	        	""")
	        	List<FlatDueDTO> findFlatsWithOutstandingDue();
}
