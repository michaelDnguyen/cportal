package com.dlvn.mcustomerportal.afragment;

import java.text.NumberFormat;
import java.util.Locale;

import com.dlvn.mcustomerportal.R;
import com.dlvn.mcustomerportal.common.Constant.PayChannel;
import com.dlvn.mcustomerportal.view.MyCustomDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PaymentOnlineFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	View view;

	EditText edtAmount;
	TextView tvError;
	Button btnNapas, btnCash, btnBank;
	Button btnMPos;

	PayChannel dlgPaymentMethod = null;
	String Amount = "";

	public PaymentOnlineFragment() {
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
	public static PaymentOnlineFragment newInstance(String param1, String param2) {
		PaymentOnlineFragment fragment = new PaymentOnlineFragment();
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
			view = inflater.inflate(R.layout.fragment_payment, container, false);
			getView(view);
			setListener();

		}

		return view;
	}

	private void getView(View view) {
		tvError = (TextView) view.findViewById(R.id.tvError);

		edtAmount = (EditText) view.findViewById(R.id.edtAmount);
		btnNapas = (Button) view.findViewById(R.id.btnNapas);
		btnCash = (Button) view.findViewById(R.id.btnCash);
		btnBank = (Button) view.findViewById(R.id.btnBank);

		btnMPos = (Button) view.findViewById(R.id.btnMPos);
	}

	private void setListener() {
		edtAmount.addTextChangedListener(new TextWatcher() {
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
					edtAmount.removeTextChangedListener(this);

					String cleanString = s.toString().replaceAll("[$,.]", "");
					double parsed = Double.parseDouble(cleanString);
					String formatted = NumberFormat.getNumberInstance(Locale.US).format(parsed);
					current = formatted;
					edtAmount.setText(formatted);
					edtAmount.setSelection(formatted.length());

					edtAmount.addTextChangedListener(this);
				}
			}
		});

		btnNapas.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (edtAmount.getText().length() > 4) {
					dlgPaymentMethod = PayChannel.NAPAS;
					btnCash.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_cash, 0, 0, 0);
					btnBank.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_bank, 0, 0, 0);
					btnNapas.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_napas, 0, R.drawable.ico_checked,
							0);
				} else {
					MyCustomDialog.Builder builder = new MyCustomDialog.Builder(getActivity());
					builder.setMessage(getString(R.string.alert_input_payment)).setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
								}
							});
					builder.create().show();
				}
			}
		});

		btnCash.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (edtAmount.getText().length() > 4) {
					dlgPaymentMethod = PayChannel.CASH;
					btnNapas.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_napas, 0, 0, 0);
					btnBank.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_bank, 0, 0, 0);
					btnCash.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_cash, 0, R.drawable.ico_checked, 0);
				} else {
					MyCustomDialog.Builder builder = new MyCustomDialog.Builder(getActivity());
					builder.setMessage(getString(R.string.alert_input_payment)).setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
								}
							});
					builder.create().show();
				}
			}
		});

		btnBank.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (edtAmount.getText().length() > 4) {
					dlgPaymentMethod = PayChannel.BANK;
					btnNapas.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_napas, 0, 0, 0);
					btnCash.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_cash, 0, 0, 0);
					btnBank.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_bank, 0, R.drawable.ico_checked, 0);
				} else {
					MyCustomDialog.Builder builder = new MyCustomDialog.Builder(getActivity());
					builder.setMessage(getString(R.string.alert_input_payment)).setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
								}
							});
					builder.create().show();
				}
			}
		});

		btnMPos.setEnabled(false);
		btnMPos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dlgPaymentMethod = PayChannel.MPOS;
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
		if (item.getItemId() == R.id.action_tiep) {
			String amount = edtAmount.getText().toString();

			if (!TextUtils.isEmpty(amount) && dlgPaymentMethod != null) {
				if (dlgPaymentMethod.equals(PayChannel.CASH) || dlgPaymentMethod.equals(PayChannel.BANK)) {

				} else if (dlgPaymentMethod.equals(PayChannel.NAPAS)) {

					PaymentInputFragment paymentInput = new PaymentInputFragment();
					Bundle bundle = new Bundle();
					bundle.putString(PaymentInputFragment.KEY_NAPAS_PAYMENT_AMOUNT, edtAmount.getText().toString());
					paymentInput.setArguments(bundle);

					if (paymentInput != null) {
						FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
								.beginTransaction();
						fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
						fragmentTransaction.replace(R.id.frame, paymentInput, paymentInput.getClass().getSimpleName());
						fragmentTransaction.addToBackStack(null);
						fragmentTransaction.commitAllowingStateLoss();
					}
				}
			} else {

				MyCustomDialog.Builder builder = new MyCustomDialog.Builder(getActivity());
				builder.setMessage(getString(R.string.alert_input_payment)).setPositiveButton("Đồng ý",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						});
				builder.create().show();
			}
		}
		return false;
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
