package com.dlvn.mcustomerportal;

import java.util.ArrayList;
import java.util.List;

import com.dlvn.mcustomerportal.adapter.model.OfficeAddressModel;
import com.dlvn.mcustomerportal.base.BaseActivity;
import com.dlvn.mcustomerportal.services.NetworkUtils;
import com.dlvn.mcustomerportal.utils.Utilities;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ListOfficeActivity extends BaseActivity implements OnMapReadyCallback {

	MapView mapView;
	GoogleMap map;

	Button btnVanPhong, btnPhongKham;

	LatLng llCurrent, llTarget;
	List<OfficeAddressModel> lstVanPhong, lstPhongKham;
	boolean isVanPhong = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_office);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		getViews();
		initDatas();
		setListener();
	}

	private void getViews() {
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);

		btnVanPhong = (Button) findViewById(R.id.btnVanPhong);
		btnPhongKham = (Button) findViewById(R.id.btnPhongKham);
	}

	private void initDatas() {
		// check network & GPS
		if (!NetworkUtils.isConnected(this))
			Utilities.showSettingsAlertConnection(this);

		btnVanPhong.setSelected(true);
		initVanPhong();
		initPhongKham();
		
		drawMarKerLocation(lstVanPhong);
	}

	private void initVanPhong() {
		if (lstVanPhong == null)
			lstVanPhong = new ArrayList<>();

		lstVanPhong.add(new OfficeAddressModel(new LatLng(10.7919258, 106.6811829), "Văn phòng cầu Công Lý",
				"11 Cầu Công Lý, phường 15, Phú Nhuận, Hồ Chí Minh, Vietnam"));
		lstVanPhong.add(new OfficeAddressModel(new LatLng(10.7960782, 106.6777852), "Van Phòng Trần Huy Liệu",
				"100 Trần Huy Liệu, phường 15, Phú Nhuận, Hồ Chí Minh, Vietnam"));
		lstVanPhong.add(new OfficeAddressModel(new LatLng(10.7906051, 106.6732979), "Văn phòng Lê Văn Sỹ",
				"42 Lê Văn sỹ p11 quận phú nhuận, Hẻm 55 Lê Văn Sỹ, Phường 11, Phú Nhuận, Hồ Chí Minh, Vietnam"));
		lstVanPhong.add(new OfficeAddressModel(new LatLng(10.7953181, 106.6723732), "Văn phòng Trương quốc Dung",
				"74 Trương Quốc Dung, Phường 10, Phú Nhuận, Hồ Chí Minh, Vietnam"));
		lstVanPhong.add(new OfficeAddressModel(new LatLng(10.7963825, 106.6814277), "Văn phòng Phan Đình Phùng",
				"241 Phan Đình Phùng, Phường 15, Phú Nhuận, Hồ Chí Minh, Vietnam"));

	}

	private void initPhongKham() {
		if (lstPhongKham == null)
			lstPhongKham = new ArrayList<>();

		lstPhongKham.add(new OfficeAddressModel(new LatLng(10.791385, 106.6781849), "Bệnh viện An Sinh",
				"10 Trần Huy Liệu, Phường 12, Hồ Chí Minh, Vietnam"));
		lstPhongKham.add(new OfficeAddressModel(new LatLng(10.7958061, 106.674981), "Phòng khám sản phụ khoa",
				"307/15, Nguyễn Văn Trỗi, Phường 11, Phú Nhuận, Hồ Chí Minh"));
		lstPhongKham.add(new OfficeAddressModel(new LatLng(10.7950724, 106.6774902), "Phòng khám mắt Hải Yến",
				"139C Nguyễn Đình Chính, Phường 8, Phú Nhuận, Hồ Chí Minh, Vietnam"));

		lstPhongKham.add(new OfficeAddressModel(new LatLng(10.7938037, 106.6741233), "Trạm Y tế phường 11 Phú Nhuận",
				"43 Trần Hữu Trang, Phường 11, Hồ Chí Minh, Vietnam"));
		lstPhongKham.add(new OfficeAddressModel(new LatLng(10.791997, 106.6771837), "Phòng khám mắt Hải Yến",
				"139C Nguyễn Đình Chính, Phường 8, Phú Nhuận, Hồ Chí Minh, Vietnam"));

	}

	private void setListener() {
		// TODO Auto-generated method stub
		btnVanPhong.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.setSelected(true);
				btnPhongKham.setSelected(false);
				if (!isVanPhong) {
					drawMarKerLocation(lstVanPhong);
					isVanPhong = true;
				}
			}
		});

		btnPhongKham.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.setSelected(true);
				btnVanPhong.setSelected(false);
				if (isVanPhong) {
					drawMarKerLocation(lstPhongKham);
					isVanPhong = false;
				}
			}
		});
	}

	@Override
	public void onMapReady(GoogleMap agr0) {

		map = agr0;
		llCurrent = new LatLng(10.794446, 106.6755217);
	}

	private void drawMarKerLocation(List<OfficeAddressModel> data) {

		if (map != null) {
			map.clear();
			
			map.addMarker(new MarkerOptions().position(llCurrent).title("You are here."));
			// zoom camera len
			CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(llCurrent, 18);
			map.animateCamera(cameraUpdate);
			
			if (data != null) {
				for (OfficeAddressModel item : data) {
					map.addMarker(new MarkerOptions().position(item.getLatlog()).title(item.getTitle()));
				}
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == android.R.id.home) {
			// finish the activity
			onBackPressed();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
