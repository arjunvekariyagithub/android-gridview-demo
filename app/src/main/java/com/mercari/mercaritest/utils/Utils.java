package com.mercari.mercaritest.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.mercari.mercaritest.R;
import java.util.concurrent.TimeoutException;

public class Utils {
    /**
     * @param throwable to identify the type of error
     * @return appropriate error message
     */
    public static String fetchErrorMessage(Context context, Throwable throwable) {
        String errorMsg = context.getResources().getString(R.string.error_msg_no_data);

        if (!isNetworkConnected(context)) {
            errorMsg = context.getResources().getString(R.string.error_msg_no_internet);
        } else if (throwable != null && throwable instanceof TimeoutException) {
            errorMsg = context.getResources().getString(R.string.error_msg_timeout);
        }
        return errorMsg;
    }

    private static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static void showLongToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
