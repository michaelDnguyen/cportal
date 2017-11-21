package com.dlvn.mcustomerportal.afragment;

import com.bumptech.glide.Glide;
import com.dlvn.mcustomerportal.R;
import com.dlvn.mcustomerportal.utils.myLog;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
	TextView tvArticle;

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

			imvAds = (ImageView) view.findViewById(R.id.imv_ads);
			Glide.with(this).load(R.drawable.daiichii_ads).into(imvAds);

			tvArticle = (TextView) view.findViewById(R.id.tv_article);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
				tvArticle.setText(Html.fromHtml(getString(R.string.home_welcom_article), Html.FROM_HTML_MODE_COMPACT));
			else
				tvArticle.setText(Html.fromHtml(getString(R.string.home_welcom_article)));

		}
		return view;
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
