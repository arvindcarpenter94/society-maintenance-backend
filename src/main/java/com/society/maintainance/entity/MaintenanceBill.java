package com.society.maintenance.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "maintenance_bill")
@Data
public class MaintenanceBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String monthYear;
    private BigDecimal ratePerSqft;
    private Integer areaSqft;
    private BigDecimal maintenanceAmount;
    private LocalDate generatedOn;
    private String status;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	public BigDecimal getRatePerSqft() {
		return ratePerSqft;
	}

	public void setRatePerSqft(BigDecimal ratePerSqft) {
		this.ratePerSqft = ratePerSqft;
	}

	public Integer getAreaSqft() {
		return areaSqft;
	}

	public void setAreaSqft(Integer areaSqft) {
		this.areaSqft = areaSqft;
	}

	public BigDecimal getMaintenanceAmount() {
		return maintenanceAmount;
	}

	public void setMaintenanceAmount(BigDecimal maintenanceAmount) {
		this.maintenanceAmount = maintenanceAmount;
	}

	public LocalDate getGeneratedOn() {
		return generatedOn;
	}

	public void setGeneratedOn(LocalDate generatedOn) {
		this.generatedOn = generatedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Flat getFlat() {
		return flat;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
	}
}
