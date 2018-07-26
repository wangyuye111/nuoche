package com.nuoche.weixin;

public class Configure {
	private static String key = "YEWe15X12TGTc7YmyIHj9pE8PUQrqUvk";
	//小程序ID	
	private static String appID = "wx97698cafd0f86482";
	//商户号
	private static String mch_id = "1505521711";
	//
	private static String secret = "e003d64707255d008bb90fade42cb652";

	public static String getSecret() {
		return secret;
	}

	public static void setSecret(String secret) {
		Configure.secret = secret;
	}

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		Configure.key = key;
	}

	public static String getAppID() {
		return appID;
	}

	public static void setAppID(String appID) {
		Configure.appID = appID;
	}

	public static String getMch_id() {
		return mch_id;
	}

	public static void setMch_id(String mch_id) {
		Configure.mch_id = mch_id;
	}

}
