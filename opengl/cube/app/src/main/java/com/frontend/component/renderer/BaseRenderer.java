// ======================================================================
// Project Name    : cube
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.renderer;
import com.frontend.component.asset.render.model.BaseModelAsset;
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
    public boolean create(BaseModelAsset renderAsset) {
        return true;
    }
    public void render(BaseModelAsset renderAsset) {
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
