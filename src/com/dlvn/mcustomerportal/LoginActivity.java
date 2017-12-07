package com.dlvn.mcustomerportal;

import java.util.Random;

import com.dlvn.mcustomerportal.base.BaseActivity;
import com.dlvn.mcustomerportal.common.cPortalPref;
import com.dlvn.mcustomerportal.services.ServicesGenerator;
import com.dlvn.mcustomerportal.services.ServicesRequest;
import com.dlvn.mcustomerportal.services.model.User;
import com.dlvn.mcustomerportal.utils.DialogUtils;
import com.dlvn.mcustomerportal.utils.Utilities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

/**
 * @arthor nn.tai
 * @date Sep 30, 2016
 */
public class LoginActivity extends BaseActivity {

	private static final String TAG = "LoginActivity";

	Button btnLogin;
	AutoCompleteTextView actAccount;
	EditText edtPassword;

	boolean isLogin = false;
	ServicesRequest svRequester;
	String deviceid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

		svRequester = ServicesGenerator.createService(ServicesRequest.class);
		deviceid = Utilities.getDeviceID(LoginActivity.this);

		actAccount = (AutoCompleteTextView) findViewById(R.id.actUsername);
		edtPassword = (EditText) findViewById(R.id.edtPassword);

		if (!TextUtils.isEmpty(cPortalPref.getUserID(LoginActivity.this)))
			actAccount.setText(cPortalPref.getUserID(LoginActivity.this));

		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				final String loginName = actAccount.getText().toString();
				String password = edtPassword.getText().toString();

				if (validateLogin(loginName, password)) {
					doApprovalLogin(loginName, password);
				}
			}
		});
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		View v = getCurrentFocus();
		if (v != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE)
				&& v instanceof EditText) {
			int scrcoords[] = new int[2];
			v.getLocationOnScreen(scrcoords);
			float x = ev.getRawX() + v.getLeft() - scrcoords[0];
			float y = ev.getRawY() + v.getTop() - scrcoords[1];

			if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
				Utilities.hideSoftInputKeyboard(this, v);
		}

		return super.dispatchTouchEvent(ev);
	}

	private boolean validateLogin(String userName, String password) {
		if (TextUtils.isEmpty(userName)) {
			DialogUtils.showAlertDialog(LoginActivity.this, getString(R.string.message_error_username_login));
			return false;
		}

		if (TextUtils.isEmpty(password)) {
			DialogUtils.showAlertDialog(LoginActivity.this, getString(R.string.message_error_username_login));
			return false;
		}

		return true;
	}

	private void doApprovalLogin(final String userID, final String password) {
		// showProgressDialog("Login...");

		User user = new User();
		user.setUserID(userID);
		user.setPassword("");
		user.setUserName(userID);
		user.setAPIToken("dadsa8d7as89");

		// random value demo
		int nhd = 0, tgt = 0, point = 0;
		Random rand = new Random();
		user.setNumberContract(rand.nextInt(10) % 10);
		user.setAmountContract((rand.nextInt(1000) % 1000) * 10000);
		user.setPoint(rand.nextInt(10000));
		user.setProposalNo("000" + rand.nextInt(99999));

		cPortalPref.saveUserLogin(LoginActivity.this, user);
		cPortalPref.setLogin(LoginActivity.this, true);
		cPortalPref.setRefreshing(LoginActivity.this, true);

		Intent i = new Intent(getBaseContext(), HomeActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(i);
		finish();

		// ApprovalLoginRequest masterData = new ApprovalLoginRequest();
		// masterData.setUserID(userID);
		// masterData.setPassword(password);
		// masterData.setAPIToken(ePaymentPref.getAPIToken(LoginActivity.this));
		// masterData.setDeviceID(Utilities.getDeviceID(LoginActivity.this));
		// masterData.setDeviceName(Utilities.getDeviceName());
		// masterData.setSystemType("");
		//
		// BaseRequest request = new BaseRequest();
		// request.setJsonDataInput(masterData);
		//
		// Call<ApprovalLoginResponse> call =
		// ePaymentApplication.getServicesRequest().ApprovalLogin(request);
		//
		// call.enqueue(new Callback<ApprovalLoginResponse>() {
		//
		// @Override
		// public void onResponse(Call<ApprovalLoginResponse> call,
		// Response<ApprovalLoginResponse> response) {
		// if (response.isSuccessful()) {
		// ApprovalLoginResponse result = response.body();
		// if (result != null) {
		// if (!TextUtils.isEmpty(result.getApprovalLoginResult().getResult()))
		// {
		//
		// if (result.getApprovalLoginResult().getResult().equals("true")) {
		//
		// User user = new User();
		// user.setUserID(userID);
		// user.setPassword("");
		// user.setUserName(result.getApprovalLoginResult().getMessage());
		// user.setAPIToken(result.getApprovalLoginResult().getNewAPIToken());
		//
		// ePaymentPref.saveUserLogin(LoginActivity.this, user);
		// ePaymentPref.setLogin(LoginActivity.this, true);
		// ePaymentPref.setRefreshing(LoginActivity.this, true);
		//
		// Intent i = new Intent(getBaseContext(), DashboardActivity.class);
		// startActivity(i);
		// finish();
		// } else if
		// (!TextUtils.isEmpty(result.getApprovalLoginResult().getMessage())) {
		//
		// if (result.getApprovalLoginResult().getMessage().equals("Login
		// Fail")) {
		// DialogUtils.showAlertDialog(LoginActivity.this,
		// getString(R.string.message_error_loginfailed));
		// } else
		// DialogUtils.showAlertDialog(LoginActivity.this,
		// result.getApprovalLoginResult().getMessage());
		// } else
		// DialogUtils.showAlertDialog(LoginActivity.this,
		// getString(R.string.message_error_loginfailed));
		// }
		// }
		// } else if (!TextUtils.isEmpty(response.message())) {
		// DialogUtils.showAlertCustomDialog(LoginActivity.this,
		// response.message());
		// }
		// hideProgressDialog();
		// }
		//
		// @Override
		// public void onFailure(Call<ApprovalLoginResponse> arg0, Throwable t)
		// {
		// if (!TextUtils.isEmpty(t.getMessage())) {
		// eLog.E("error " + t.getMessage());
		//
		// if (t.getMessage().equals(BaseResponse.NO_INTERNET))
		// DialogUtils.showAlertDialog(LoginActivity.this,
		// getString(R.string.message_error_no_internet));
		// else
		// DialogUtils.showAlertDialog(LoginActivity.this,
		// getString(R.string.message_error_connect_service));
		// }
		// hideProgressDialog();
		// }
		// });
	}
}
