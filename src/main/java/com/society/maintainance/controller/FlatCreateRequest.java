package com.society.maintenance.controller;

public class FlatCreateRequest {

	private String flatNumber;
    private String ownerName;
    private Integer areaSqft;
	public String getFlatNumber() {
		return flatNumber;
	}
	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public Integer getAreaSqft() {
		return areaSqft;
	}
	public void setAreaSqft(Integer areaSqft) {
		this.areaSqft = areaSqft;
	}
}
