package com.dlvn.mcustomerportal;

import com.dlvn.mcustomerportal.adapter.model.ContractModel;
import com.dlvn.mcustomerportal.base.BaseActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ContractDetailActivity extends BaseActivity {

	public static final String KEY_PROPOSAL = "";

	ContractModel itemHopDong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contract_detail);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getViews();
		initData();
		setListener();
	}

	private void getViews() {
		// TODO Auto-generated method stub

	}

	private void initData() {
		// TODO Auto-generated method stub
		if (getIntent().getExtras().containsKey("CONTRACT_DETAIL"))
			itemHopDong = getIntent().getParcelableExtra("CONTRACT_DETAIL");

		if(itemHopDong != null)
			setTitle("Chi tiết hợp đồng " + itemHopDong.getSoHopDong());
	}

	private void setListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
