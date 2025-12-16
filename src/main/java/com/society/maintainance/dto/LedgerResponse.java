package com.society.maintenance.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LedgerResponse {

	private LocalDate date;
    private String type;
    private BigDecimal debit;
    private BigDecimal credit;
    private BigDecimal balance;
    
	public LedgerResponse(LocalDate date, String type, BigDecimal debit, BigDecimal credit, BigDecimal balance) {
		super();
		this.date = date;
		this.type = type;
		this.debit = debit;
		this.credit = credit;
		this.balance = balance;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getDebit() {
		return debit;
	}
	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
