package com.society.maintenance.dto;

import java.util.List;

public class BulkOpeningBalanceRequest {

	private List<OpeningBalanceItem> items;

	public List<OpeningBalanceItem> getItems() {
		return items;
	}

	public void setItems(List<OpeningBalanceItem> items) {
		this.items = items;
	}
}
