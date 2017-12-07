package com.dlvn.mcustomerportal.adapter.model;

public class ContractModel {

	String soHopDong;
	String tenSanPham;
	boolean isActive;
	long amount;
	String activeDate;
	String endDate;

	public ContractModel(String soHopDong, String tenSanPham, boolean isActive, long amount, String activeDate,
			String endDate) {
		super();
		this.soHopDong = soHopDong;
		this.tenSanPham = tenSanPham;
		this.isActive = isActive;
		this.amount = amount;
		this.activeDate = activeDate;
		this.endDate = endDate;
	}

	public ContractModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSoHopDong() {
		return soHopDong;
	}

	public void setSoHopDong(String soHopDong) {
		this.soHopDong = soHopDong;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(String activeDate) {
		this.activeDate = activeDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
