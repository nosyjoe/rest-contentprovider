package com.filzip.client.android;

import android.util.Log;

/**
 * @author Philipp Engel <philipp@filzip.com>
 */
public class MuLog {

    public static void d(Object from, String msg) {
        Log.d(Setup.LOG_TAG, "["+from.getClass().getSimpleName()+"] " + msg);
    }

    public static void i(Object from, String msg) {
        Log.i(Setup.LOG_TAG, "["+from.getClass().getSimpleName()+"] " + msg);
    }

    public static void w(Object from, String msg) {
        Log.w(Setup.LOG_TAG, "["+from.getClass().getSimpleName()+"] " + msg);
    }

    public static void e(Object from, String msg) {
        Log.e(Setup.LOG_TAG, "["+from.getClass().getSimpleName()+"] " + msg);
    }

}
