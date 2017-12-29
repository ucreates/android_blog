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
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.renderscript.Float3;
import android.util.AttributeSet;
import com.core.timer.TimeInterval;
import com.frontend.behaviour.BaseBehaviour;
import com.frontend.component.camera.GLES2Camera;
import java.util.ArrayList;
import java.util.Map;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
public class GLES2View extends BaseGLView implements GLSurfaceView.Renderer {
    private Map<Integer, BaseBehaviour> behaviourMap;
    public GLES2View(Context context) {
        super(context);
        return;
    }
    public GLES2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        return;
    }
    public void create(Map<Integer, BaseBehaviour> behaviourMap, Float3 position, float fov , float near , float far) {
        this.behaviourMap = behaviourMap;
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
        for (Integer id : this.behaviourMap.keySet()) {
            BaseBehaviour behaviour = this.behaviourMap.get(id);
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
        ArrayList<Integer> destroyBehaviourIdList = new ArrayList<Integer>();
        for (Integer id : this.behaviourMap.keySet()) {
            BaseBehaviour behaviour = this.behaviourMap.get(id);
            if (false != behaviour.destroy) {
                destroyBehaviourIdList.add(behaviour.id);
                continue;
            }
            if (null != behaviour.collider) {
                for (Integer otherBehaviourId : this.behaviourMap.keySet()) {
                    BaseBehaviour otherBehaviour = this.behaviourMap.get(otherBehaviourId);
                    if (behaviour.id == otherBehaviour.id || null == otherBehaviour.collider) {
                        continue;
                    }
                    boolean intersect = behaviour.collider.isIntersected(otherBehaviour.renderAsset);
                    if (false == intersect) {
                        if (false != behaviour.intersect && otherBehaviour.id == behaviour.intersectedBehaviourId) {
                            behaviour.onCollisionExit(otherBehaviour);
                            behaviour.intersect = false;
                            behaviour.intersectedBehaviourId = -1;
                        }
                        continue;
                    } else {
                        if (false == behaviour.intersect) {
                            behaviour.onCollisionEnter(otherBehaviour);
                            behaviour.intersect = true;
                            behaviour.intersectedBehaviourId = otherBehaviour.id;
                        } else {
                            behaviour.onCollisionStay(otherBehaviour);
                        }
                    }
                }
            }
            behaviour.onUpdate(delta);
            behaviour.onRender();
        }
        for (Integer id : destroyBehaviourIdList) {
            this.behaviourMap.remove(id);
        }
        return;
    }
}
