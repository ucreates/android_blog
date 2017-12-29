// ======================================================================
// Project Name    : cube
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.view;
import android.app.Activity;
public abstract class BaseView {
    protected Activity parentActivity;
    public void setActivity(Activity activity) {
        this.parentActivity = activity;
        return;
    }
}
