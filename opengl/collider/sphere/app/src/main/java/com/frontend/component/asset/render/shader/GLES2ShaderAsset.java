// ======================================================================
// Project Name    : sphere
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.asset.render.shader;
import android.content.Context;
import android.opengl.GLES20;
import com.core.entity.component.renderer.VertexBufferObject;
import com.core.io.file.TextStream;
import com.core.utility.TraceUtility;
import com.frontend.component.asset.render.BaseRenderAsset;
public class GLES2ShaderAsset extends BaseRenderAsset {
    private int handle;
    private Context context;
    private String path;
    private int type;
    public GLES2ShaderAsset(Context context, String shaderAssetPath, int shaderType) {
        this.handle = 0;
        this.context = context;
        this.path = shaderAssetPath;
        this.type = shaderType;
    }
    public void compile() {
        TextStream stream = new TextStream(this.context);
        String source = stream.read(this.path);
        int handle = GLES20.glCreateShader(this.type);
        if (0 == handle) {
            return;
        }
        GLES20.glShaderSource(handle, source);
        GLES20.glCompileShader(handle);
        int[] status = new int[1];
        GLES20.glGetShaderiv(handle, GLES20.GL_COMPILE_STATUS, status, 0);
        if (GLES20.GL_FALSE == status[0]) {
            String description = GLES20.glGetShaderInfoLog(handle);
            GLES20.glDeleteShader(handle);
            this.handle = 0;
            TraceUtility.log(description);
            return;
        }
        this.handle = handle;
        return;
    }
    public int getHandle() {
        return this.handle;
    }
    public void updateMatrix(int programHandle, String uniformName, float[] matrixArray) {
        int location = GLES20.glGetUniformLocation(programHandle, uniformName);
        GLES20.glUniformMatrix4fv(location, 1, false, matrixArray, 0);
        return;
    }
    public void updateTexture(int programHandle, String uniformName) {
        int location = GLES20.glGetUniformLocation(programHandle, uniformName);
        GLES20.glUniform1i(location, 1);
        return;
    }
    public void updateVertex(int programHandle, String attributeName, int size, int type, VertexBufferObject vbo) {
        int pointer = 0;
        if (size == VertexBufferObject.COORDS_PER_VERTEX) {
            pointer =  VertexBufferObject.POSITION_START_POINTER;
        } else if (size == VertexBufferObject.COLORS_PER_VERTEX) {
            pointer =  VertexBufferObject.COLOR_START_POINTER;
        } else if (size == VertexBufferObject.UV_PER_VERTEX) {
            pointer =  VertexBufferObject.UV_START_POINTER;
        }
        this.updateVertex(programHandle, attributeName, size, type, vbo.stride, pointer);
        return;
    }
    public void updateVertex(int programHandle, String attributeName, int size, int type, int stride, int pointer) {
        int location = GLES20.glGetAttribLocation(programHandle, attributeName);
        GLES20.glEnableVertexAttribArray(location);
        GLES20.glVertexAttribPointer(location, size, type, false, stride, pointer);
        return;
    }
    public void disableVertex(int programHandle, String attributeName) {
        int location = GLES20.glGetAttribLocation(programHandle, attributeName);
        GLES20.glDisableVertexAttribArray(location);
        return;
    }
}
