package com.dlvn.mcustomerportal.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

public abstract class BaseActivity extends AppCompatActivity  {
	private Toolbar mToolbar;
	// protected TabLayout mTabLayout;
	//
	// protected boolean isShowTabbar() {
	// return false;
	// }
	//
	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_base);
	//
	// initToolbar();
	// container = (RelativeLayout) findViewById(R.id.container);
	// initView();
	//
	// }
	//
	// void initToolbar() {
	// mToolbar = (Toolbar) findViewById(R.id.toolbar);
	// setActionBar(mToolbar);
	//
	// if (isShowTabbar()) {
	//
	// mTabLayout = (TabLayout) findViewById(R.id.tabs);
	// mTabLayout.setVisibility(View.VISIBLE);
	//
	// }
	// }

	ProgressDialog mProgressDialog;

	protected void showProgressDialog(String message) {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setCanceledOnTouchOutside(false);
		}
		mProgressDialog.setMessage(message);
		mProgressDialog.show();
	}

	protected void hideProgressDialog() {
		if (mProgressDialog == null)
			throw new RuntimeException();
		mProgressDialog.dismiss();
	}

}
