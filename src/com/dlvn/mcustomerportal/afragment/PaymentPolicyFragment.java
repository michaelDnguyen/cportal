package com.dlvn.mcustomerportal.afragment;

import com.dlvn.mcustomerportal.R;
import com.dlvn.mcustomerportal.common.cPortalPref;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class PaymentPolicyFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	View view;

	TextView tvTenKhachHang, tvMaKhachHang;
	CheckedTextView ctvAccept;
	Button btnTiepTuc;

	public PaymentPolicyFragment() {
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
	public static PaymentPolicyFragment newInstance(String param1, String param2) {
		PaymentPolicyFragment fragment = new PaymentPolicyFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		if (view == null) {
			view = inflater.inflate(R.layout.fragment_payment_policy, container, false);
			getView(view);
			setListener();

		}

		return view;
	}

	private void getView(View view) {
		ctvAccept = (CheckedTextView) view.findViewById(R.id.ctvAccept);

		tvTenKhachHang = (TextView) view.findViewById(R.id.tvTenKhachHang);
		tvMaKhachHang = (TextView) view.findViewById(R.id.tvMaKhachHang);

		btnTiepTuc = (Button) view.findViewById(R.id.btnTiepTuc);

		if (cPortalPref.haveLogin(getActivity())) {
			tvTenKhachHang.setText(cPortalPref.getUserName(getActivity()));
			tvMaKhachHang.setText(cPortalPref.getUserProposal(getActivity()));
		}
	}

	private void setListener() {
		ctvAccept.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ctvAccept.isChecked())
					ctvAccept.setChecked(false);
				else
					ctvAccept.setChecked(true);
			}
		});

		btnTiepTuc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ctvAccept.isChecked()) {
					PaymentOnlineFragment fragment = new PaymentOnlineFragment();
					if (fragment != null) {
						FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
								.beginTransaction();
						fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
						fragmentTransaction.replace(R.id.frame, fragment, fragment.getClass().getSimpleName());
						fragmentTransaction.addToBackStack(null);
						fragmentTransaction.commitAllowingStateLoss();
						
					}
				}
			}
		});
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.payment, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
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
