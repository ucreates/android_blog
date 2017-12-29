// ======================================================================
// Project Name    : box
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
import com.frontend.component.asset.render.model.GLES2TextureAsset;
import com.frontend.component.asset.render.shader.GLES2ShaderAsset;
import com.frontend.component.collider.BoxCollider;
import com.frontend.component.renderer.GLES2Renderer;
import com.frontend.component.vfx.spline.Bezier;
public class BlueTextureBehaviour extends BaseBehaviour {
    private float time;
    public BlueTextureBehaviour(Context context) {
        super(context);
        this.id = 1;
        this.time = 0;
    }
    @Override
    public void onCreate() {
        GLES2ShaderAsset vertexShaderAsset = new GLES2ShaderAsset(this.context, "sharders/texture/vertex_shader.glsl", GLES20.GL_VERTEX_SHADER);
        GLES2ShaderAsset fragmentShaderAsset = new GLES2ShaderAsset(this.context, "sharders/texture/fragment_shader.glsl", GLES20.GL_FRAGMENT_SHADER);
        this.renderAsset = new GLES2TextureAsset(this.context, "textures/texture02.png");
        this.renderAsset.create();
        this.renderAsset.transform.position = new Float3(2.0f, 0.0f, 0.0f);
        this.collider = new BoxCollider(this.renderAsset);
        this.renderer = new GLES2Renderer();
        this.renderer.setVertexShader(vertexShaderAsset);
        this.renderer.setFragmentShader(fragmentShaderAsset);
        this.renderer.create(this.renderAsset);
        return;
    }
    @Override
    public void onUpdate(TimeInterval delta) {
        Float3 npos = Bezier.create(this.time, 5.0f, new Float3(2.0f, 0.0f, -5.0f), new Float3(1.0f, 5.0f, -2.5f), new Float3(0.0f, 0.0f, 0.0f));
        float nrx = this.renderAsset.transform.rotation.x + 5f;
        float nry = this.renderAsset.transform.rotation.y + 5f;
        float nrz = this.renderAsset.transform.rotation.z + 5f;
        this.renderAsset.transform.position = npos;
        this.renderAsset.transform.rotation = new Float3(nrx, nry, nrz);
        this.time += 0.1f;
        return;
    }
    @Override
    public void onCollisionEnter(BaseBehaviour behaviour) {
        this.destroy = true;
        return;
    }
}
