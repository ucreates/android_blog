// ======================================================================
// Project Name    : sphere
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.view;
import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
public abstract class BaseGLView extends GLSurfaceView {
    protected Activity parentActivity;
    public BaseGLView(Context context) {
        super(context);
        return;
    }
    public BaseGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        return;
    }
    public void setActivity(Activity activity) {
        this.parentActivity = activity;
        return;
    }
}
