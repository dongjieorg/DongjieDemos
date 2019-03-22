package com.dongjie.dongjiedemos.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/18.
 */

public class ToastUtils {

	public static void showToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}
