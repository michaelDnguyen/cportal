package com.dlvn.mcustomerportal;

import com.dlvn.mcustomerportal.base.BaseActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

public class ContractDetailActivity extends BaseActivity {
	
	public static final String KEY_PROPOSAL = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contract_detail);
		initActionBar();
		getViews();
		initData();
		setListener();
	}

	private void initActionBar() {
		ActionBar actionBAr = getSupportActionBar();
		
		actionBAr.setTitle("Chi tiết hợp đồng");
		actionBAr.setHomeButtonEnabled(true);
		actionBAr.setDisplayHomeAsUpEnabled(true);
		actionBAr.setDisplayShowHomeEnabled(true);

		actionBAr.setHomeAsUpIndicator(R.drawable.ic_back_white);
	}

	private void getViews() {
		// TODO Auto-generated method stub

	}

	private void initData() {
		// TODO Auto-generated method stub

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
