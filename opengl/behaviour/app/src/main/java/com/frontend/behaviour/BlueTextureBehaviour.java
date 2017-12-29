// ======================================================================
// Project Name    : behaviour
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
import android.renderscript.Float3;
import com.core.timer.TimeInterval;
import com.core.utility.TraceUtility;
import com.frontend.component.asset.render.model.TextureAsset;
import com.frontend.component.asset.render.shader.GLES2ShaderAsset;
import com.frontend.component.renderer.GLES2Renderer;
public class BlueTextureBehaviour extends BaseBehaviour {
    private float counter;
    public BlueTextureBehaviour(Context context) {
        super(context);
        this.counter = 0.0f;
    }
    @Override
    public void onCreate() {
        GLES2ShaderAsset vertexShaderAsset = new GLES2ShaderAsset(this.context, "sharders/texture/vertex_shader.glsl", GLES20.GL_VERTEX_SHADER);
        GLES2ShaderAsset fragmentShaderAsset = new GLES2ShaderAsset(this.context, "sharders/texture/fragment_shader.glsl", GLES20.GL_FRAGMENT_SHADER);
        this.renderAsset = new TextureAsset(this.context, "textures/texture02.png");
        this.renderAsset.create();
        this.renderAsset.transform.position = new Float3(1.0f, 0.0f, 0.0f);
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
    @Override
    public void onUpdate(TimeInterval delta) {
        this.counter += 0.1f;
        float nx = this.renderAsset.transform.position.x;
        float ny = (float)Math.cos(this.counter);
        float nz = this.renderAsset.transform.position.z;
        this.renderAsset.transform.position = new Float3(nx, ny, nz);
        return;
    }
}
