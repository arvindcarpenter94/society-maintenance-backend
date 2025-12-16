package com.society.maintenance.dto;

import java.math.BigDecimal;

public class DashboardSummary {

    private BigDecimal totalOutstanding;
    private BigDecimal totalAdvance;
    private Long flatsWithDue;
    
	public DashboardSummary(BigDecimal totalOutstanding, BigDecimal totalAdvance, Long flatsWithDue) {
		super();
		this.totalOutstanding = totalOutstanding;
		this.totalAdvance = totalAdvance;
		this.flatsWithDue = flatsWithDue;
	}
	public BigDecimal getTotalOutstanding() {
		return totalOutstanding;
	}
	public void setTotalOutstanding(BigDecimal totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
	}
	public BigDecimal getTotalAdvance() {
		return totalAdvance;
	}
	public void setTotalAdvance(BigDecimal totalAdvance) {
		this.totalAdvance = totalAdvance;
	}
	public Long getFlatsWithDue() {
		return flatsWithDue;
	}
	public void setFlatsWithDue(Long flatsWithDue) {
		this.flatsWithDue = flatsWithDue;
	}
}

