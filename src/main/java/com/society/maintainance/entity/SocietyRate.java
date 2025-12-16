package com.society.maintenance.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "society_rate")
@Data
public class SocietyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal ratePerSqft;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getRatePerSqft() {
		return ratePerSqft;
	}
	public void setRatePerSqft(BigDecimal ratePerSqft) {
		this.ratePerSqft = ratePerSqft;
	}
	public LocalDate getEffectiveFrom() {
		return effectiveFrom;
	}
	public void setEffectiveFrom(LocalDate effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
	public LocalDate getEffectiveTo() {
		return effectiveTo;
	}
	public void setEffectiveTo(LocalDate effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
}
