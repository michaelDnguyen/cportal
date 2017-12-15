package com.dlvn.mcustomerportal.adapter.model;

import com.google.android.gms.maps.model.LatLng;

public class OfficeAddressModel {

	LatLng latlog;
	String title;
	String address;

	public OfficeAddressModel(LatLng latlog, String title, String address) {
		super();
		this.latlog = latlog;
		this.title = title;
		this.address = address;
	}

	public OfficeAddressModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LatLng getLatlog() {
		return latlog;
	}

	public void setLatlog(LatLng latlog) {
		this.latlog = latlog;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}
