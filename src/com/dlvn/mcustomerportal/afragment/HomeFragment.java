package com.dlvn.mcustomerportal.afragment;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.dlvn.mcustomerportal.R;
import com.dlvn.mcustomerportal.adapter.HomeListAdapter;
import com.dlvn.mcustomerportal.adapter.listener.RecyclerViewClickListener;
import com.dlvn.mcustomerportal.adapter.listener.RecyclerViewTouchListener;
import com.dlvn.mcustomerportal.adapter.model.HomeItemModel;
import com.dlvn.mcustomerportal.utils.myLog;
import com.dlvn.mcustomerportal.view.DividerItemDecoration;
import com.dlvn.mcustomerportal.view.RecyclerSmoothLayoutManager;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

	RecyclerView rvContent;
	HomeListAdapter rvAdapter;

	List<HomeItemModel> lstData;

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

			rvContent = (RecyclerView) view.findViewById(R.id.rvContent);
			RecyclerView.LayoutManager layout = new RecyclerSmoothLayoutManager(getActivity(),
					LinearLayoutManager.VERTICAL, false);
			rvContent.setLayoutManager(layout);

			DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvContent.getContext());
			rvContent.addItemDecoration(dividerItemDecoration);

			initData();
			setListener();
		}
		return view;
	}

	private void initData() {
		lstData = new ArrayList<HomeItemModel>();

		lstData.add(new HomeItemModel("CHÀO MỪNG QUÝ KHÁCH ĐẾN VỚI \"CỔNG THÔNG TIN KHÁCH HÀNG\"",
				"Với mục tiêu phát triển mối quan hệ gắn kết với Khách hàng tham gia bảo hiểm thông qua việc cập nhật thường xuyên cho Khách hàng tất cả những thông tin về hợp đồng bảo hiểm của mình, Dai-ichi Life Việt Nam trân trọng giới thiệu đến toàn thể Quý Khách hàng trang thông tin trực tuyến dành cho Khách hàng \"Cổng thông tin Khách hàng\"",
				"https://thue.today/media/images/section/brand/168706156912166_dai-ichi-life-viet-nam-cover.png"));
		lstData.add(new HomeItemModel("BẢN TIN DAI-ICHI-LIFE VIỆT NAM", "Kính mời quý khách hàng xem bản tin Dai-Ichi-Life Việt Nam", "https://viecoi.vn/jobs/jobfullview/userdata/jobs/5273/241216-002.jpg"));
		lstData.add(new HomeItemModel("HƯỚNG DẪN SỬ DỤNG CỔNG THÔNG TIN DỊCH VỤ KHÁCH HÀNG",
				"Hướng dẫn sử dụng các chức năng của cổng thông tin khách hàng như đăng nhập, thay đổi mật khẩu, sửa đổi thông tin, thông tin hợp đồng, chương trình điểm thưởng... ",
				"http://baohiemtuonglai.vn/wp-content/uploads/2017/06/tuyen-nhan-vien-kinh-doanh-luong-cao-2.jpg"));
		lstData.add(new HomeItemModel("HƯỚNG DẪN NỘP PHÍ BẢO HIỂM ĐỊNH KỲ",
				"Để hợp đồng bảo hiểm được duy trì hiệu lực liên tục nhằm đảm bảo các quyền lợi bảo hiểm của mình, Quý khách vui lòng nộp phí bảo hiểm định kỳ đúng hạn và có thể lựa chọn...",
				"https://www.baohiem-dai-ichi-life.com/wp-content/uploads/2016/05/3phut-tu-van-bao-hiem-nhan-tho-dai-ichi-life-nhat-ban12.png"));
		lstData.add(new HomeItemModel("CÁC LOẠI BIỂU MẪU",
				"Cung cấp các loại biểu mẫu cho khách hàng như đăng kí dịch vụ SMS, yêu cầu sử dụng điểm thưởng, giải quyết quyền lợi bảo hiểm, thanh toán quyền lợi hợp đồng bảo hiểm...",
				"https://dai-ichi-life.com.vn/images/news/165/1701/attribute/74/Outpatient-Healthcare.jpg"));

		rvAdapter = new HomeListAdapter(getActivity(), lstData);
		rvContent.setAdapter(rvAdapter);
	}

	private void setListener() {
		rvContent.addOnItemTouchListener(
				new RecyclerViewTouchListener(getActivity(), rvContent, new RecyclerViewClickListener() {

					@Override
					public void onLongClick(View view, int position) {
						
					}

					@Override
					public void onClick(View view, int position) {
						
					}
				}));
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
