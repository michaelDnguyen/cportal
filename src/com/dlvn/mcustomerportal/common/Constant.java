package com.dlvn.mcustomerportal.common;

public class Constant {

	/************************** MAIN URL API Services *****************************************/
	public static final String URL_DEV = "http://10.166.2.119:7800/iibmobile/v1/";
	public static final String URL_UAT = "http://magpcmsuat2.dai-ichi-life.com.vn/iibmobile/v1/";
	public static final String URL_PRD = "";

	/********************* URL PAYMENT NAPAS ************************/
	// UAT
	public static final String URL_NAPAS_UAT = "http://khuat.dai-ichi-life.com.vn:8090/mpayment.aspx";
	// PRD
	public static final String URL_NAPAS_PRD = "http://khuat.dai-ichi-life.com.vn:8090/mpayment.aspx";
	// URL NAPAS RESPONSE UAT
	public static final String URL_NAPAS_RESPONSE_UAT = "http://khuat.dai-ichi-life.com.vn/onlinepayment/vpc_dr.aspx?";
	// URL NAPAS RESPONSE PRD
	public static final String URL_NAPAS_RESPONSE_PRD = "http://khuat.dai-ichi-life.com.vn/onlinepayment/vpc_dr.aspx?";

	//URL API
	public static final String URL = URL_UAT;
	// URL NAPAS
	public static String URL_PAYMENT = URL_NAPAS_UAT;
	// URL NAPAS Resonse
	public static String URL_PAYMENT_RES = URL_NAPAS_RESPONSE_UAT;

	// flag for print Log
	public static final boolean DEBUG = true;

	// Constant for grant permission multi choice gallery
	public static final int PERMISSION_REQUEST_CODE = 2000;
	public static final int PERMISSION_GRANTED = 2001;
	public static final int PERMISSION_DENIED = 2002;
	
	/**
	 * enum các kênh thanh toán được hỗ trợ
	 * @author nn.tai
	 * @modify Nov 21, 2017
	 */
	public enum PayChannel {
		CASH("Tiền mặt", "Cash", ""), BANK("Ngân Hàng", "Bank", ""), NAPAS("Napas/Thẻ nội địa", "Napas",
				""), MPOS("mPOS", "mPos", "");

		private String stringName;
		private String stringValue;
		private String ID;

		private PayChannel(String toString, String value, String toID) {
			stringName = toString;
			stringValue = value;
			ID = toID;
		}

		@Override
		public String toString() {
			return stringName;
		}

		public String getStringValue() {
			return stringValue;
		}

		public String getID() {
			return ID;
		}

		public static PayChannel fromValue(String value) {
			for (PayChannel mo : values()) {
				if (mo.stringValue.equals(value)) {
					return mo;
				}
			}
			return CASH;
		}
	}

}
