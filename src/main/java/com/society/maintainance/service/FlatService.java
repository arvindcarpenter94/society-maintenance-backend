package com.society.maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.maintenance.controller.FlatCreateRequest;
import com.society.maintenance.dto.BulkResult;
import com.society.maintenance.entity.Flat;
import com.society.maintenance.repository.FlatRepository;

import jakarta.transaction.Transactional;

@Service
public class FlatService {
	
	@Autowired
	private FlatRepository repo;

	@Transactional
    public BulkResult bulkCreate(List<FlatCreateRequest> requests) {

        int created = 0;
        int skipped = 0;

        for (FlatCreateRequest req : requests) {

            if (repo.existsByFlatNumber(req.getFlatNumber())) {
                skipped++;
                continue;
            }

            Flat flat = new Flat();
            flat.setFlatNumber(req.getFlatNumber());
            flat.setOwnerName(req.getOwnerName());
            flat.setAreaSqft(req.getAreaSqft());

            repo.save(flat);
            created++;
        }

        return new BulkResult(created, skipped);
    }
}
