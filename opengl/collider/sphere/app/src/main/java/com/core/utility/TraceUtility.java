// ======================================================================
// Project Name    : sphere
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.core.utility;
import android.util.Log;
public class TraceUtility {
    public final static String IDENTIFIER = "ANDROID_FOUNDATION";
    public static void log(String val) {
        Log.i(TraceUtility.IDENTIFIER, val);
        return;
    }
    public static void log(String title, String val) {
        Log.i(TraceUtility.IDENTIFIER, title + "::" + val);
        return;
    }
}
