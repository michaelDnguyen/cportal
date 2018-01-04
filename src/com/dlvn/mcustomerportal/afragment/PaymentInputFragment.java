package com.dlvn.mcustomerportal.afragment;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.dlvn.mcustomerportal.R;
import com.dlvn.mcustomerportal.WebNapasActivity;
import com.dlvn.mcustomerportal.common.Constant;
import com.dlvn.mcustomerportal.common.cPortalPref;
import com.dlvn.mcustomerportal.common.Constant.FeeFrequency;
import com.dlvn.mcustomerportal.utils.myLog;
import com.dlvn.mcustomerportal.view.MyCustomDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentInputFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	public static final int REQUEST_NAPAS_PAYMENT = 3886;
	public static final String KEY_NAPAS_PAYMENT = "KEY_NAPAS_PAYMENT_SUCCESS";
	public static final String KEY_NAPAS_PAYMENT_AMOUNT = "KEY_NAPAS_PAYMENT_AMOUNT";
	public static final String KEY_NAPAS_PAYMENT_TRANSID = "KEY_NAPAS_PAYMENT_TRANSID";

	public static final String KEY_NAPAS_PAYMENT_URL = "NAPAS_PAYMENT_URL";

	public static final int RESULTCODE_PAYMENT_FALSE = 7777;
	public static final int RESULTCODE_PAYMENT_SUCCESS = 8386;

	EditText edtHotenNguoiNop, edtPhoneNumber, edtEmail, edtBMBH;
	TextView tvProposalNo, tvHinhThuc, tvSoTienThanhToan;

	View view;
	Spinner spnDinhKyDP;
	Button btnThanhToan;

	String uniqueValue = "";
	String Amount = "";

	public PaymentInputFragment() {
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
	public static PaymentInputFragment newInstance(String param1, String param2) {
		PaymentInputFragment fragment = new PaymentInputFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		if (view == null) {
			view = inflater.inflate(R.layout.fragment_payment_input, container, false);
			getView(view);
			initDatas();
			setListener();

		}

		return view;
	}

	private void getView(View view) {
		edtHotenNguoiNop = (EditText) view.findViewById(R.id.edtHotenNguoiNop);
		edtPhoneNumber = (EditText) view.findViewById(R.id.edtPhoneNumber);
		edtEmail = (EditText) view.findViewById(R.id.edtEmail);
		edtBMBH = (EditText) view.findViewById(R.id.edtBMBH);

		tvProposalNo = (TextView) view.findViewById(R.id.tvProposalNo);
		tvSoTienThanhToan = (TextView) view.findViewById(R.id.tvSoTienThanhToan);
		tvHinhThuc = (TextView) view.findViewById(R.id.tvHinhThuc);

		spnDinhKyDP = (Spinner) view.findViewById(R.id.spnDinhKyDongPhi);
		
		btnThanhToan = (Button) view.findViewById(R.id.btnThanhToan);
	}

	private void initDatas() {
		if (getArguments() != null) {
			if (getArguments().containsKey(KEY_NAPAS_PAYMENT_AMOUNT))
				Amount = getArguments().getString(KEY_NAPAS_PAYMENT_AMOUNT);
		}

		if (!TextUtils.isEmpty(Amount))
			tvSoTienThanhToan.setText(Amount);

		tvProposalNo.setText(cPortalPref.getUserProposal(getActivity()));
		edtHotenNguoiNop.setText(cPortalPref.getUserName(getActivity()));
		edtPhoneNumber.setText("0987654123");
		
		List<String> list = new ArrayList<String>();
		list.add(FeeFrequency.YEARLY.toString());
		list.add(FeeFrequency.HALF_YEARLY.toString());
		list.add(FeeFrequency.QUARTERLY.toString());
		list.add(FeeFrequency.MONTHLY.toString());
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnDinhKyDP.setAdapter(dataAdapter);
		spnDinhKyDP.setPrompt("Chọn");
	}

	private void setListener() {
		btnThanhToan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					if (validatePayment()) {
						
						String feeFrequency = FeeFrequency.fromName((String)spnDinhKyDP.getSelectedItem()).getStringValue();
						myLog.E("FeeFrequency = " + feeFrequency);

						String url;
						url = initPaymentRequest(tvProposalNo.getText().toString(),
								edtHotenNguoiNop.getText().toString(), edtPhoneNumber.getText().toString(),
								edtBMBH.getText().toString(), tvSoTienThanhToan.getText().toString().replace(",", ""), feeFrequency);

						Intent napas = new Intent(getActivity(), WebNapasActivity.class);
						napas.putExtra(KEY_NAPAS_PAYMENT_URL, url);

						startActivityForResult(napas, REQUEST_NAPAS_PAYMENT);
					} else
						Toast.makeText(getActivity(), "Vui lòng nhập số tiền thanh toán", 3000).show();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		});

		tvSoTienThanhToan.addTextChangedListener(new TextWatcher() {
			private String current = "";

			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (!s.toString().equals(current) && !TextUtils.isEmpty(s.toString())) {
					tvSoTienThanhToan.removeTextChangedListener(this);

					String cleanString = s.toString().replaceAll("[$,.]", "");
					double parsed = Double.parseDouble(cleanString);
					String formatted = NumberFormat.getNumberInstance(Locale.US).format(parsed);
					current = formatted;
					tvSoTienThanhToan.setText(formatted);

					tvSoTienThanhToan.addTextChangedListener(this);
				}
			}
		});
	}

	private String initPaymentRequest(String proposalNo, String payer, String phone, String poName, String amount, String feeFrequency)
			throws UnsupportedEncodingException {

		StringBuffer buf = new StringBuffer();
		buf.append(Constant.URL_PAYMENT);
		buf.append("?");

		buf.append("sProposalNo=" + Base64.encodeToString(proposalNo.getBytes("UTF-8"), Base64.DEFAULT));
		buf.append("&");
		buf.append("sPayer=" + Base64.encodeToString(payer.getBytes("UTF-8"), Base64.DEFAULT));
		buf.append("&");
		buf.append("sPhoneNumber=" + Base64.encodeToString(phone.getBytes("UTF-8"), Base64.DEFAULT));
		buf.append("&");
		buf.append("sPOName=" + Base64.encodeToString(poName.getBytes("UTF-8"), Base64.DEFAULT));
		buf.append("&");
		buf.append("sAmount=" + Base64.encodeToString(amount.getBytes("UTF-8"), Base64.DEFAULT));

		buf.append("&");
		buf.append("sFCCode=" + Base64.encodeToString("110555".getBytes("UTF-8"), Base64.DEFAULT));
		buf.append("&");
		buf.append("sAgentName=" + Base64.encodeToString("Nguyen Van Teo".getBytes("UTF-8"), Base64.DEFAULT));
		buf.append("&");
		buf.append("sAgentType=" + Base64.encodeToString("BM".getBytes("UTF-8"), Base64.DEFAULT));
		//Add fee frequency
		buf.append("&");
		buf.append("sFeeFrequency=" + Base64.encodeToString(feeFrequency.getBytes("UTF-8"), Base64.DEFAULT));
		return buf.toString();
	}

	private boolean validatePayment() {

		if (TextUtils.isEmpty(edtHotenNguoiNop.getText().toString())) {
			MyCustomDialog.Builder builder = new MyCustomDialog.Builder(getActivity());
			builder.setMessage("Anh/chị vui lòng nhập tên người nộp tiền").setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			builder.create().show();
			return false;
		}

		if (TextUtils.isEmpty(edtBMBH.getText().toString())) {
			MyCustomDialog.Builder builder = new MyCustomDialog.Builder(getActivity());
			builder.setMessage("Anh/chị vui lòng nhập tên người mua bảo hiểm").setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}

					});
			builder.create().show();
			return false;
		}

		return true;
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
