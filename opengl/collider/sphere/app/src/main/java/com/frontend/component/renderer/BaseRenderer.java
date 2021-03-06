// ======================================================================
// Project Name    : sphere
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.renderer;
import com.frontend.component.asset.render.model.GLES2BaseModelAsset;
import com.frontend.component.asset.render.shader.GLES2ShaderAsset;
public abstract class BaseRenderer {
    protected GLES2ShaderAsset vertexSharder;
    protected GLES2ShaderAsset fragmentShader;
    protected int programHandle;
    public BaseRenderer() {
        this.vertexSharder = null;
        this.fragmentShader = null;
        this.programHandle = 0;
    }
    public boolean create(GLES2BaseModelAsset renderAsset) {
        return true;
    }
    public void render(GLES2BaseModelAsset renderAsset) {
        return;
    }
    public void setVertexShader(GLES2ShaderAsset shader) {
        this.vertexSharder = shader;
        return;
    }
    public void setFragmentShader(GLES2ShaderAsset shader) {
        this.fragmentShader = shader;
        return;
    }
}
