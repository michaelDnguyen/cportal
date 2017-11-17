package com.dlvn.mcustomerportal.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.Locale;

import com.dlvn.mcustomerportal.base.CPortalApplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @arthor nn.tai
 * @date Oct 3, 2016
 */
public class Utilities {

	/**
	 * set Height cho listview item, sau khi set xong sẽ ko cần scrollbar
	 * cho listview
	 * 
	 * @param listView
	 * @param padding
	 *            - padding between items
	 * @arthor nn.tai
	 * @date Oct 20, 2016 9:43:01 AM
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView, int padding) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
			return;

		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.EXACTLY);
		int totalHeight = 0;
		View view = null;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			view = listAdapter.getView(i, null, listView);
			if (i == 0)
				view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

			view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
			totalHeight += view.getMeasuredHeight() + padding;
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	/**
	 * set Height cho expand listview item, sau khi set xong sẽ ko cần
	 * scrollbar cho Expand listview
	 * 
	 * @param listView
	 * @param group
	 * @arthor nn.tai
	 * @date Oct 20, 2016 9:44:53 AM
	 */
	public static void setExpListViewHeightForAllChild(ExpandableListView listView, int group) {
		ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
		int totalHeight = 0;
		int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.EXACTLY);
		for (int i = 0; i < listAdapter.getGroupCount(); i++) {
			View groupItem = listAdapter.getGroupView(i, false, null, listView);
			groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

			totalHeight += groupItem.getMeasuredHeight();

			if (((listView.isGroupExpanded(i)) && (i != group)) || ((!listView.isGroupExpanded(i)) && (i == group))) {
				for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
					View listItem = listAdapter.getChildView(i, j, false, null, listView);
					listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

					totalHeight += listItem.getMeasuredHeight();

				}
			}
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		int height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
		if (height < 10)
			height = 200;
		params.height = height;
		listView.setLayoutParams(params);
		listView.requestLayout();

	}

	/**
	 * get File name from Path to file
	 * 
	 * @param path
	 * @return
	 * @arthor nn.tai
	 * @date Oct 20, 2016 9:45:22 AM
	 */
	public static String getFileNameFromPath(String path) {
		String result = "";
		String[] s = path.split("/");
		if (s.length > 0)
			result = s[s.length - 1];
		return result;
	}

	/**
	 * Hide keyboard has show when focus view
	 * 
	 * @param c
	 * @param view
	 * @arthor nn.tai
	 * @date Oct 20, 2016 9:45:56 AM
	 */
	public static void hideSoftInputKeyboard(Context c, View view) {
		if (view != null) {
			InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	/**
	 * Get version OS of device
	 * 
	 * @return
	 * @arthor nn.tai
	 * @date Oct 25, 2016 3:09:54 PM
	 */
	public static String GetVersion() {
		String version = String.valueOf(Build.VERSION.SDK_INT);
		if (version == null || version.isEmpty()) {
			return "No version";
		}

		if (version.equals("25"))
			return "Android7.1";
		else if (version.equals("24"))
			return "Android7.0";
		else if (version.equals("23"))
			return "Android6.0";
		else if (version.equals("22"))
			return "Android5.1";
		else if (version.equals("21"))
			return "Android5.0";
		else if (version.equals("20"))
			return "Android4.4W";
		else if (version.equals("19"))
			return "Android4.4";
		else if (version.equals("18"))
			return "Android4.3";
		else if (version.equals("17"))
			return "Android4.2";
		else if (version.equals("16"))
			return "Android4.1";
		else if (version.equals("15") || version.equals("14"))
			return "Android4.0";
		return version;
	}

	/**
	 * get Device Name
	 * 
	 * @return
	 * @arthor nn.tai
	 * @date Oct 25, 2016 3:12:25 PM
	 */
	public static String getDeviceName() {
		try {
			String manufacturer = Build.MANUFACTURER;
			String model = Build.MODEL;

			if (model.contains("Unknown") || manufacturer.contains("Unknown")) {
				return "No find device";
			}

			if (model.startsWith(manufacturer)) {
				return capitalize(model);
			} else {
				return capitalize(manufacturer) + "_" + model;
			}
		} catch (Exception ex) {
			return "No find device";
		}
	}

	/**
	 * get Device ID of device
	 * 
	 * @param context
	 * @return
	 * @arthor nn.tai
	 * @date Oct 25, 2016 3:43:50 PM
	 */
	public static String getDeviceID(Context context) {
		return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
	}

	public static String capitalize(String s) {
		if (TextUtils.isEmpty(s)) {
			return "";
		}
		char first = s.charAt(0);
		if (Character.isUpperCase(first)) {
			return s;
		} else {
			return Character.toUpperCase(first) + s.substring(1);
		}
	}

	/**
	 * Format number by Locate US
	 * 
	 * @param number
	 * @return
	 * @arthor nn.tai
	 * @date Nov 29, 2016 2:25:15 PM
	 */
	public static String formatNumberbyLocate(String number) {
		if (TextUtils.isEmpty(number))
			return "0";
		NumberFormat df = NumberFormat.getNumberInstance(Locale.US);
		try {
			double num = Double.parseDouble(number);
			return df.format(num);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return number;
		}
	}

	/**
	 * delete all file and folder in directory in path
	 * 
	 * @param path
	 * @arthor nn.tai
	 * @date Nov 29, 2016 2:24:44 PM
	 */
	public static void deleteAllFileInFolder(String path) {
		if (!TextUtils.isEmpty(path)) {
			File dir = new File(path);
			myLog.W("delete " + dir.getAbsolutePath());
			if (dir.isDirectory()) {
				for (File file : dir.listFiles())
					deleteAllFileInFolder(file.getAbsolutePath());
			} else if (!dir.delete()) {
				new FileNotFoundException("Failed to delete file: " + dir);
			}
			dir.delete();
		}
	}

	/**
	 * process case No INTERNET or NO SERVICE, send user to login screen
	 * 
	 * @param context
	 * @param message
	 * @arthor nn.tai
	 * @date Dec 13, 2016 9:25:00 AM
	 */
	public static void processLoginAgain(final Context context, final String message) {
		((Activity) context).runOnUiThread(new Runnable() {

			@Override
			public void run() {
				try {
					DialogUtils.showAlertDialogWithCallback(context, message, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							CPortalApplication.getInstance().logout();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Process finish Activity current (Detail Act) to gome Summary Act
	 * 
	 * @param context
	 * @param message
	 * @arthor nn.tai
	 * @date Dec 22, 2016 5:56:25 PM
	 */
	public static void processHomeAgain(final Context context, final String message) {
		((Activity) context).runOnUiThread(new Runnable() {

			@Override
			public void run() {
				try {
					DialogUtils.showAlertDialogWithCallback(context, message, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							((Activity) context).finish();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
