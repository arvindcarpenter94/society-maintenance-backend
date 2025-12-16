package com.society.maintenance.dto;

import java.math.BigDecimal;

public class OpeningBalanceItem {

	private String flatNumber;

    private BigDecimal openingBalance;

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	public BigDecimal getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(BigDecimal openingBalance) {
		this.openingBalance = openingBalance;
	}
}
