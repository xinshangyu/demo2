package com.example.baselibrary.zh.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringUtils {

	public static String byte2hex(byte[] b) {

		String str = "";
		String stmp = "";

		int length = b.length;

		for (int n = 0; n < length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				str += "0";
			}
			str += stmp;
		}

		return str.toLowerCase();
	}

	public static String nullToStrTrim(String str) {

		if (str == null) {
			str = "";
		}

		return str.trim();
	}

	public static boolean isNotEmpty(String str) {

		return ((str != null) && (str.trim().length() > 0));
	}
	
	public static boolean isEmpty(String str) {

		return !isNotEmpty(str);
	}

	public static int getRealLength(String str) {

		return getRealLength(str, "utf-8");
	}

	public static int getRealLength(String str, String charsetName) {

		str = nullToStrTrim(str);

		try {
			return str.getBytes(charsetName).length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static String encode(String str, String enc) {

		String strEncode = "";

		try {
			if (str != null)
				strEncode = URLEncoder.encode(str, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return strEncode;
	}

	public static String decode(String str, String enc) {

		String strDecode = "";

		try {
			if (str != null)
				strDecode = URLDecoder.decode(str, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return strDecode;
	}

	public static String encode(String str) {

		return encode(str, "utf-8");
	}

	public static String decode(String str) {

		return decode(str, "utf-8");
	}
}
