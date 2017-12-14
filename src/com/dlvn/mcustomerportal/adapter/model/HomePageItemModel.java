package com.dlvn.mcustomerportal.adapter.model;

public class HomePageItemModel {

	String maHopDong;
	String tongPhiDaDong;

	String phiCoBan;
	String ngayDongPhi;

	String ngayGiaHan;
	String ngayConlai;

	public HomePageItemModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HomePageItemModel(String maHopDong, String tongGTHienTai, String phiCoBan, String ngayDongPhi,
			String ngayGiaHan, String ngayConlai) {
		super();
		this.maHopDong = maHopDong;
		this.tongPhiDaDong = tongGTHienTai;
		this.phiCoBan = phiCoBan;
		this.ngayDongPhi = ngayDongPhi;
		this.ngayGiaHan = ngayGiaHan;
		this.ngayConlai = ngayConlai;
	}

	public String getMaHopDong() {
		return maHopDong;
	}

	public void setMaHopDong(String maHopDong) {
		this.maHopDong = maHopDong;
	}

	public String getTongPhiDaDong() {
		return tongPhiDaDong;
	}

	public void setTongPhiDaDong(String tongPhiDaDong) {
		this.tongPhiDaDong = tongPhiDaDong;
	}

	public String getPhiCoBan() {
		return phiCoBan;
	}

	public void setPhiCoBan(String phiCoBan) {
		this.phiCoBan = phiCoBan;
	}

	public String getNgayDongPhi() {
		return ngayDongPhi;
	}

	public void setNgayDongPhi(String ngayDongPhi) {
		this.ngayDongPhi = ngayDongPhi;
	}

	public String getNgayGiaHan() {
		return ngayGiaHan;
	}

	public void setNgayGiaHan(String ngayGiaHan) {
		this.ngayGiaHan = ngayGiaHan;
	}

	public String getNgayConlai() {
		return ngayConlai;
	}

	public void setNgayConlai(String ngayConlai) {
		this.ngayConlai = ngayConlai;
	}

}
