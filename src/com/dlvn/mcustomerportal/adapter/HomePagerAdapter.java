package com.dlvn.mcustomerportal.adapter;

import java.util.List;

import com.dlvn.mcustomerportal.R;
import com.dlvn.mcustomerportal.adapter.model.HomePageItemModel;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomePagerAdapter extends PagerAdapter {

	List<HomePageItemModel> data;
	Context context;
	LayoutInflater inflater;

	public HomePagerAdapter(Context c, List<HomePageItemModel> lst) {
		super();
		context = c;
		data = lst;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View v;
		v = inflater.inflate(R.layout.item_page_home, null);
		container.addView(v);

		return v;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);

	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		super.finishUpdate(container);
	}

	@Override
	public void startUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		super.startUpdate(container);
	}
}
