package com.dongjie.dongjiedemos.tools;

import android.text.TextUtils;
import android.util.Log;

public class LogUtils {
	private static boolean debug = true;
	public static void setDebug(boolean isDebug) {
		debug = isDebug;
	}

	public static boolean isDebug() {
		return debug;
	}

	public static void showLog(String text) {
		if (debug) {
			if(!TextUtils.isEmpty(text)&&text.length() > 3000) {
				for(int i=0;i<text.length();i+=3000){
					if(i+3000<text.length())
						Log.d("dongjie",text.substring(i, i+3000));
					else
						Log.d("dongjie",text.substring(i, text.length()));
				}
			} else
				Log.d("dongjie",text);
		}
	}

	public static void showErrLog(String text) {
		if (debug) {
			Log.e("dongjie", text);
		}
	}

	public static void showInfoLog(String text){
		if (debug) {
			if(!TextUtils.isEmpty(text)&&text.length() > 3000) {
				for(int i=0;i<text.length();i+=3000){
					if(i+3000<text.length())
						Log.i("dongjie",text.substring(i, i+3000));
					else
						Log.i("dongjie",text.substring(i, text.length()));
				}
			} else
				Log.i("dongjie",text);
		}
	}
}
