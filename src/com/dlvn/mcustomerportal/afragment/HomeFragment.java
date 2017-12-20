package com.dlvn.mcustomerportal.afragment;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.dlvn.mcustomerportal.R;
import com.dlvn.mcustomerportal.adapter.HomePagerAdapter;
import com.dlvn.mcustomerportal.adapter.model.HomeItemModel;
import com.dlvn.mcustomerportal.adapter.model.HomePageItemModel;
import com.dlvn.mcustomerportal.common.cPortalPref;
import com.dlvn.mcustomerportal.utils.myLog;
import com.dlvn.mcustomerportal.view.indicator.CirclePageIndicator;
import com.dlvn.mcustomerportal.view.indicator.ScrollerViewPager;
import com.dlvn.mcustomerportal.view.indicator.SpringIndicator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String TAG = "HomeFragment";
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	View view;
	ImageView imvAds;

	LinearLayout lloHopDong, lloTransaction;
	RelativeLayout rloThongTinChung;
	ScrollerViewPager viewPager;
	SpringIndicator springIndicator;
	CirclePageIndicator circleIndicator;
	HomePagerAdapter pagerAdapter;
	
	TextView tvWelcome, tvDescription;

	List<HomeItemModel> lstData;
	List<HomePageItemModel> lstPagerData;

	public HomeFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 *
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment HomeFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static HomeFragment newInstance(String param1, String param2) {
		myLog.E(TAG, "newInstance");

		HomeFragment fragment = new HomeFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myLog.E(TAG, "onCreate");
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myLog.E(TAG, "onCreateView");

		if (view == null) {
			view = inflater.inflate(R.layout.fragment_home, container, false);

			getViews(view);
			initData();
			setListener();
		}
		return view;
	}
	
	/**
	 * get view from layout
	 * @author nn.tai
	 * @date Dec 14, 2017
	 * @param view
	 */
	private void getViews(View view){
		
		rloThongTinChung = (RelativeLayout) view.findViewById(R.id.rloThongTinChung);
		lloHopDong = (LinearLayout) view.findViewById(R.id.lloHopDong);
		lloTransaction = (LinearLayout) view.findViewById(R.id.lloTransaction);
		
		viewPager = (ScrollerViewPager) view.findViewById(R.id.view_pager);
//		springIndicator = (SpringIndicator) view.findViewById(R.id.indicator);
		circleIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
		
		tvWelcome = (TextView) view.findViewById(R.id.tvWelcome);
		tvDescription = (TextView) view.findViewById(R.id.tvDescription);

		imvAds = (ImageView) view.findViewById(R.id.imv_ads);
		Glide.with(this).load(R.drawable.daiichii_ads).into(imvAds);

	}

	/**
	 * init data to views
	 * @author nn.tai
	 * @date Dec 14, 2017
	 */
	private void initData() {
		
		if (cPortalPref.haveLogin(getActivity())) {
			tvWelcome.setText("Chào mừng " + cPortalPref.getUserName(getActivity()));
			tvDescription.setText(getActivity().getString(R.string.home_welcome_user));
			lloHopDong.setVisibility(View.VISIBLE);
			lloTransaction.setVisibility(View.VISIBLE);
		} else {
			tvWelcome.setText("Chào mừng Quý khách");
			tvDescription.setText(getActivity().getString(R.string.home_welcome_guest));
			lloHopDong.setVisibility(View.GONE);
			lloTransaction.setVisibility(View.GONE);
		}
		
		//init viewpager
		lstPagerData = new ArrayList<>();
		lstPagerData.add(new HomePageItemModel("00078941","236,500,000","3,200,000","23/12/2017","23/01/2018","39"));
		lstPagerData.add(new HomePageItemModel("001034198","688,105,024","36,215,500","23/10/2017","22/12/2017","8"));
		lstPagerData.add(new HomePageItemModel("001036845","56,331,000","2,814,400","02/11/2017","01/01/2018","18"));

		pagerAdapter = new HomePagerAdapter(getActivity(), lstPagerData);
		viewPager.setAdapter(pagerAdapter);
		viewPager.fixScrollSpeed();

		// just set viewPager
		circleIndicator.setViewPager(viewPager);
	}

	/**
	 * function set listener for view
	 * @author nn.tai
	 * @date Dec 14, 2017
	 */
	private void setListener() {
		rloThongTinChung.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InfoGeneralFragment fragment = new InfoGeneralFragment();
				if (fragment != null) {
					FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
					fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
					fragmentTransaction.replace(R.id.frame, fragment, fragment.getClass().getName());
					fragmentTransaction.commitAllowingStateLoss();
				}
			}
		});
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		myLog.E(TAG, "onAttach");
		// if (context instanceof OnFragmentInteractionListener) {
		// mListener = (OnFragmentInteractionListener) context;
		// } else {
		// throw new RuntimeException(context.toString()
		// + " must implement OnFragmentInteractionListener");
		// }
	}

	@Override
	public void onDetach() {
		super.onDetach();
		myLog.E(TAG, "onDetach");
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}

}
