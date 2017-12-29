// ======================================================================
// Project Name    : sphere
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.asset.render.model;
import com.core.entity.component.asset.Transform;
import com.core.entity.component.renderer.GLESColor;
import com.core.entity.component.renderer.IndexBufferObject;
import com.core.entity.component.renderer.VertexBufferObject;
import com.frontend.component.asset.render.BaseRenderAsset;
public class GLES2BaseModelAsset extends BaseRenderAsset {
    public static final int CUBE = 1;
    public static final int TEXTURE = 2;
    public VertexBufferObject vbo;
    public IndexBufferObject ibo;
    public float[] vertices;
    public short[] indices;
    public Transform transform;
    public GLESColor color;
    public int type;
    public GLES2BaseModelAsset() {
    }
    public GLES2BaseModelAsset(int renderAssetType) {
        this.color = new GLESColor(1f, 1f, 1f, 1f);
        this.type = renderAssetType;
    }
    @Override
    public void create() {
        this.vbo = new VertexBufferObject();
        this.vbo.create(this.vertices);
        this.ibo = new IndexBufferObject();
        this.ibo.create(this.indices);
        this.transform = new Transform();
        return;
    }
}
