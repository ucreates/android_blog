// ======================================================================
// Project Name    : cube
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.behaviour;
import android.content.Context;
import android.opengl.GLES20;
import com.core.utility.TraceUtility;
import com.frontend.component.asset.render.model.CubeAsset;
import com.frontend.component.asset.render.shader.GLES2ShaderAsset;
import com.frontend.component.renderer.GLES2Renderer;
public class CubeBehaviour  extends BaseBehaviour {
    public CubeBehaviour(Context context) {
        super(context);
    }
    @Override
    public void onCreate() {
        GLES2ShaderAsset vertexShaderAsset = new GLES2ShaderAsset(this.context, "sharders/cube/vertex_shader.glsl", GLES20.GL_VERTEX_SHADER);
        GLES2ShaderAsset fragmentShaderAsset = new GLES2ShaderAsset(this.context, "sharders/cube/fragment_shader.glsl", GLES20.GL_FRAGMENT_SHADER);
        this.renderAsset = new CubeAsset();
        this.renderAsset.create();
        this.renderer = new GLES2Renderer();
        this.renderer.setVertexShader(vertexShaderAsset);
        this.renderer.setFragmentShader(fragmentShaderAsset);
        boolean ret = this.renderer.create(this.renderAsset);
        if (false == ret) {
            TraceUtility.log("faild create renderer");
            return;
        }
        return;
    }
}
