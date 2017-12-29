// ======================================================================
// Project Name    : behaviour
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.camera;
import android.opengl.GLES20;
import android.renderscript.Float3;
import android.renderscript.Matrix4f;
import com.core.entity.component.asset.Transform;
import com.core.entity.component.renderer.GLESColor;
public class GLES2Camera {
    private static GLES2Camera instance = null;
    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;
    private float aspectRatio;
    private float fov;
    private float near;
    private float far;
    private GLESColor clearColor;
    private Transform transform;
    private GLES2Camera() {
        this.transform = new Transform();
        this.clearColor = new GLESColor(0.0f, 0.0f, 0.0f, 1.0f);
        this.viewMatrix = new Matrix4f();
        this.projectionMatrix = new Matrix4f();
        this.aspectRatio = 0f;
        this.fov = 0f;
        this.near = 0f;
        this.far = 0f;
    }
    public static GLES2Camera getInstance() {
        if (null == GLES2Camera.instance) {
            GLES2Camera.instance = new GLES2Camera();
        }
        return GLES2Camera.instance;
    }
    public void update() {
        this.projectionMatrix.loadPerspective(this.fov, this.aspectRatio, this.near, this.far);
        this.viewMatrix.loadTranslate(this.transform.position.x, this.transform.position.y, this.transform.position.z);
        return;
    }
    public void clear() {
        GLES20.glClearColor(this.clearColor.r, this.clearColor.g, this.clearColor.b, this.clearColor.a);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        return;
    }
    public Matrix4f getProjectionMatrix() {
        return this.projectionMatrix;
    }
    public Matrix4f getViewMatrix() {
        return this.viewMatrix;
    }
    public void setPosition(Float3 position) {
        this.transform.position = position;
        return;
    }
    public void setPosition(float x, float y, float z) {
        this.transform.position = new Float3(x, y, z);
        return;
    }
    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
        return;
    }
    public void setClearColor(GLESColor color) {
        this.clearColor = color;
        return;
    }
    public void setFov(float fov) {
        this.fov = fov;
        return;
    }
    public void setClippingPlane(float near, float far) {
        this.near = near;
        this.far = far;
        return;
    }
}
