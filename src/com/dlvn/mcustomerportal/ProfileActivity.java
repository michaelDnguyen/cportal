package com.dlvn.mcustomerportal;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.dlvn.mcustomerportal.base.BaseActivity;
import com.dlvn.mcustomerportal.common.cPortalPref;
import com.dlvn.mcustomerportal.utils.myLog;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends BaseActivity {

	ImageView imvProfile;
	TextView tvName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		getViews();
		initData();
		setListener();
	}

	private void getViews() {
		// TODO Auto-generated method stub
		imvProfile = (ImageView) findViewById(R.id.imvProfile);
		tvName = (TextView) findViewById(R.id.tvName);
	}

	private void initData() {
		// TODO Auto-generated method stub
		// Loading profile image
		Glide.with(this).load(HomeActivity.urlProfile).thumbnail(0.5f).apply(RequestOptions.circleCropTransform())
				.listener(new RequestListener<Drawable>() {

					@Override
					public boolean onLoadFailed(GlideException arg0, Object arg1, Target<Drawable> arg2, boolean arg3) {
						// progress.setVisibility(View.GONE);
						myLog.E("Load Image Failed!");
						return false;
					}

					@Override
					public boolean onResourceReady(Drawable arg0, Object arg1, Target<Drawable> arg2, DataSource arg3,
							boolean arg4) {
						// progress.setVisibility(View.GONE);
						myLog.E("Load Image Ready!");
						return false;
					}
				}).into(imvProfile);

		tvName.setText(cPortalPref.getUserName(this));
	}

	private void setListener() {
		// TODO Auto-generated method stub

	}
}