// ======================================================================
// Project Name    : behaviour
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.view;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.renderscript.Float3;
import android.util.AttributeSet;
import com.core.timer.TimeInterval;
import com.frontend.behaviour.BaseBehaviour;
import com.frontend.component.camera.GLES2Camera;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
public class GLES2View extends BaseGLView implements GLSurfaceView.Renderer {
    private ArrayList<BaseBehaviour> behaviourList;
    public GLES2View(Context context) {
        super(context);
        return;
    }
    public GLES2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        return;
    }
    public void create(ArrayList<BaseBehaviour> behaviourList, Float3 position, float fov , float near , float far) {
        this.behaviourList = behaviourList;
        GLES2Camera camera = GLES2Camera.getInstance();
        camera.setPosition(position);
        camera.setFov(fov);
        camera.setClippingPlane(near, far);
        camera.update();
        this.setEGLContextClientVersion(2);
        this.setPreserveEGLContextOnPause(true);
        this.setRenderer(this);
        return;
    }
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        for (BaseBehaviour behaviour : this.behaviourList) {
            behaviour.onCreate();
        }
        return;
    }
    @Override
    public void onSurfaceChanged(GL10 gl10, int w, int h) {
        float aspectRatio = (float)w / (float)h;
        GLES2Camera camera = GLES2Camera.getInstance();
        camera.setAspectRatio(aspectRatio);
        return;
    }
    @Override
    public void onDrawFrame(GL10 gl10) {
        TimeInterval delta = TimeInterval.getInstance();
        GLES2Camera camera = GLES2Camera.getInstance();
        delta.update();
        camera.clear();
        camera.update();
        for (BaseBehaviour behaviour : this.behaviourList) {
            behaviour.onUpdate(delta);
            behaviour.onRender();
        }
        return;
    }
}
