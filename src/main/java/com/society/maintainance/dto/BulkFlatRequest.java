package com.society.maintenance.dto;

import java.util.List;

import com.society.maintenance.controller.FlatCreateRequest;

public class BulkFlatRequest {

	private List<FlatCreateRequest> flats;

	public List<FlatCreateRequest> getFlats() {
		return flats;
	}

	public void setFlats(List<FlatCreateRequest> flats) {
		this.flats = flats;
	}
}
