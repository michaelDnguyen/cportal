package com.dlvn.mcustomerportal;

import java.util.ArrayList;
import java.util.List;

import com.dlvn.mcustomerportal.base.BaseActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Bonus_NopPhiBHActivity extends BaseActivity {

	
	Spinner spnHopDong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bonus__nop_phi_bh);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	
		getViews();
		initDatas();
		setListener();
	}

	
	private void getViews() {
		// TODO Auto-generated method stub
		spnHopDong = (Spinner) findViewById(R.id.spnHopDong);
	}


	private void initDatas() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("00658947");
		list.add("00864521");
		list.add("00247846");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnHopDong.setAdapter(dataAdapter);
	}


	private void setListener() {
		// TODO Auto-generated method stub
		
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
