package com.society.maintenance.dto;

public class BulkResult {

	private int created;
    private int skipped;
    
	public BulkResult(int created, int skipped) {
		super();
		this.created = created;
		this.skipped = skipped;
	}
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
	}
	public int getSkipped() {
		return skipped;
	}
	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}
}
