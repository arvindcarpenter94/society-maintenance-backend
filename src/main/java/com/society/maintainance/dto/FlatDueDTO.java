package com.society.maintenance.dto;

import java.math.BigDecimal;

public class FlatDueDTO {
    private Long flatId;
    private String flatNumber;
    private String ownerName;
    private BigDecimal balance;
    
	public FlatDueDTO(Long flatId, String flatNumber, String ownerName, BigDecimal balance) {
		super();
		this.flatId = flatId;
		this.flatNumber = flatNumber;
		this.ownerName = ownerName;
		this.balance = balance;
	}
	public Long getFlatId() {
		return flatId;
	}
	public void setFlatId(Long flatId) {
		this.flatId = flatId;
	}
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
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}

