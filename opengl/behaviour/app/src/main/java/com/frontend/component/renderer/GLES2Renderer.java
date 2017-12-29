// ======================================================================
// Project Name    : behaviour
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.renderer;
import android.opengl.GLES20;
import android.renderscript.Matrix4f;
import com.core.entity.component.renderer.Vertex;
import com.core.entity.component.renderer.VertexBufferObject;
import com.core.utility.TraceUtility;
import com.frontend.component.asset.render.model.BaseModelAsset;
import com.frontend.component.asset.render.model.TextureAsset;
import com.frontend.component.camera.GLES2Camera;
public class GLES2Renderer extends BaseRenderer {
    @Override
    public boolean create(BaseModelAsset renderAsset) {
        if (null == this.vertexSharder || null == this.fragmentShader) {
            TraceUtility.log("invalid shader");
            return false;
        }
        this.vertexSharder.compile();
        this.fragmentShader.compile();
        this.programHandle = GLES20.glCreateProgram();
        int vhandle = vertexSharder.getHandle();
        int fhandle = fragmentShader.getHandle();
        GLES20.glAttachShader(this.programHandle, vhandle);
        GLES20.glAttachShader(this.programHandle, fhandle);
        if (BaseModelAsset.CUBE == renderAsset.type) {
            GLES20.glBindAttribLocation(this.programHandle, Vertex.Attributes.POSITION, "a_Position");
            GLES20.glBindAttribLocation(this.programHandle, Vertex.Attributes.COLOR, "a_Color");
        } else if (BaseModelAsset.TEXTURE == renderAsset.type) {
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
            GLES20.glBindAttribLocation(this.programHandle, Vertex.Attributes.POSITION, "a_Position");
            GLES20.glBindAttribLocation(this.programHandle, Vertex.Attributes.COLOR, "a_Color");
            GLES20.glBindAttribLocation(this.programHandle, Vertex.Attributes.TEXCOORD, "a_TexCoord");
        }
        GLES20.glLinkProgram(this.programHandle);
        int[] linked = new int[1];
        GLES20.glGetProgramiv(this.programHandle, GLES20.GL_LINK_STATUS, linked, 0);
        if (GLES20.GL_FALSE == linked[0]) {
            String description = GLES20.glGetProgramInfoLog(this.programHandle);
            GLES20.glDeleteProgram(this.programHandle);
            TraceUtility.log(description);
            return  false;
        }
        return true;
    }
    @Override
    public void render(BaseModelAsset renderAsset) {
        GLES2Camera camera = GLES2Camera.getInstance();
        Matrix4f projectionMatrix = camera.getProjectionMatrix();
        Matrix4f viewMatrix = camera.getViewMatrix();
        Matrix4f modelMatrix = renderAsset.transform.getMatrix();
        Matrix4f modelViewMatrix =  new Matrix4f();
        modelViewMatrix.multiply(viewMatrix);
        modelViewMatrix.multiply(modelMatrix);
        float[] pmtx = projectionMatrix.getArray();
        float[] mvmtx = modelViewMatrix.getArray();
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        GLES20.glUseProgram(this.programHandle);
        if (BaseModelAsset.CUBE == renderAsset.type) {
            this.vertexSharder.updateMatrix(this.programHandle, "u_ProjectionMatrix", pmtx);
            this.vertexSharder.updateMatrix(this.programHandle, "u_ModelViewMatrix", mvmtx);
            this.vertexSharder.updateVertex(this.programHandle, "a_Position", VertexBufferObject.COORDS_PER_VERTEX, GLES20.GL_FLOAT, renderAsset.vbo);
            this.vertexSharder.updateVertex(this.programHandle, "a_Color", VertexBufferObject.COLORS_PER_VERTEX, GLES20.GL_FLOAT, renderAsset.vbo);
        } else if (BaseModelAsset.TEXTURE == renderAsset.type) {
            TextureAsset textureAsset = (TextureAsset)renderAsset;
            int textureId = textureAsset.getTextureId();
            GLES20.glActiveTexture(GLES20.GL_TEXTURE1);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
            this.vertexSharder.updateMatrix(this.programHandle, "u_ProjectionMatrix", pmtx);
            this.vertexSharder.updateMatrix(this.programHandle, "u_ModelViewMatrix", mvmtx);
            this.vertexSharder.updateVertex(this.programHandle, "a_Position", VertexBufferObject.COORDS_PER_VERTEX, GLES20.GL_FLOAT, renderAsset.vbo);
            this.vertexSharder.updateVertex(this.programHandle, "a_Color", VertexBufferObject.COLORS_PER_VERTEX, GLES20.GL_FLOAT, renderAsset.vbo);
            this.vertexSharder.updateVertex(this.programHandle, "a_TexCoord", VertexBufferObject.UV_PER_VERTEX, GLES20.GL_FLOAT, renderAsset.vbo);
            this.fragmentShader.updateTexture(this.programHandle, "u_Texture");
        }
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, renderAsset.vbo.id);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, renderAsset.ibo.id);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, renderAsset.indices.length, GLES20.GL_UNSIGNED_SHORT, 0);
        if (BaseModelAsset.CUBE == renderAsset.type) {
            this.vertexSharder.disableVertex(this.programHandle, "a_Position");
            this.vertexSharder.disableVertex(this.programHandle, "a_Color");
        } else if (BaseModelAsset.TEXTURE == renderAsset.type) {
            GLES20.glDisable(GLES20.GL_TEXTURE_2D);
            this.vertexSharder.disableVertex(this.programHandle, "a_Position");
            this.vertexSharder.disableVertex(this.programHandle, "a_Color");
            this.vertexSharder.disableVertex(this.programHandle, "a_TexCoord");
        }
        GLES20.glDisable(GLES20.GL_DEPTH_TEST);
        GLES20.glDisable(GLES20.GL_CULL_FACE);
        GLES20.glDisable(GLES20.GL_BLEND);
        GLES20.glUseProgram(0);
        return;
    }
}
