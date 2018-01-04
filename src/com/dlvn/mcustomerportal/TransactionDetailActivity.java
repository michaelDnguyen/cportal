package com.dlvn.mcustomerportal;

import com.dlvn.mcustomerportal.base.BaseActivity;

import android.os.Bundle;
import android.view.MenuItem;

public class TransactionDetailActivity extends BaseActivity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_detail);
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
		
	}

	private void setListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
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
