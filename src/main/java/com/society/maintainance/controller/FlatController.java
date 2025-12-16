package com.society.maintenance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.maintenance.entity.Flat;
import com.society.maintenance.repository.FlatRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/flats")
@RequiredArgsConstructor
public class FlatController {

	@Autowired
    private FlatRepository repo;

    @PostMapping
    public Flat create(@RequestBody Flat flat) {
        return repo.save(flat);
    }

    @GetMapping
    public List<Flat> getAll() {
    	return repo.findAllSortedNumerically();
    }
}
