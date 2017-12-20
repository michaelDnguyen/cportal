package com.dlvn.mcustomerportal;

import com.dlvn.mcustomerportal.base.BaseActivity;
import com.dlvn.mcustomerportal.utils.Utilities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class ContactActivity extends BaseActivity {

	LinearLayout lloCall;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		getViews();
		initDatas();
		setListener();
	}

	private void getViews() {
		// TODO Auto-generated method stub
		lloCall = (LinearLayout) findViewById(R.id.lloCall);
	}

	private void initDatas() {
		// TODO Auto-generated method stub
		
	}

	private void setListener() {
		// TODO Auto-generated method stub
		lloCall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Utilities.actionCallPhoneNumber(ContactActivity.this, "02838100888");
			}
		});
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
