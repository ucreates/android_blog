// ======================================================================
// Project Name    : cube
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.asset.render.model;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import java.io.IOException;
import java.io.InputStream;
public class TextureAsset extends BaseModelAsset {
    private Context context;
    private String textureAssetPath;
    private int textureId;
    public TextureAsset(Context context, String textureAssetPath) {
        super(BaseModelAsset.TEXTURE);
        this.context = context;
        this.textureAssetPath = textureAssetPath;
    }
    @Override
    public void create() {
        float[] vercities = {
            // Front
            0.5f, -0.5f, 0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 1, 0, // 0
            0.5f, 0.5f, 0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 1, 1, // 1
            -0.5f, 0.5f, 0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 0, 1, // 2
            -0.5f, -0.5f, 0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 0, 0, // 3
            // Back
            -0.5f, -0.5f, -0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 1, 0, // 4
            -0.5f, 0.5f, -0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 1, 1, // 5
            0.5f, 0.5f, -0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 0, 1, // 6
            0.5f, -0.5f, -0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 0, 0, // 7
            // Left
            -0.5f, -0.5f, 0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 1, 0, // 8
            -0.5f, 0.5f, 0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 1, 1, // 9
            -0.5f, 0.5f, -0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 0, 1, // 10
            -0.5f, -0.5f, -0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 0, 0, // 11
            // Right
            0.5f, -0.5f, -0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 1, 0, // 8
            0.5f, 0.5f, -0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 1, 1, // 9
            0.5f, 0.5f, 0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 0, 1, // 10
            0.5f, -0.5f, 0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 0, 0, // 11
            // Top
            0.5f, 0.5f, 0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 1, 0, // 8
            0.5f, 0.5f, -0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 1, 1, // 9
            -0.5f, 0.5f, -0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 0, 1, // 10
            -0.5f, 0.5f, 0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 0, 0, // 11
            // Bottom
            0.5f, -0.5f, -0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 1, 0, // 12
            0.5f, -0.5f, 0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 1, 1, // 13
            -0.5f, -0.5f, 0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 0, 1, // 14
            -0.5f, -0.5f, -0.5f, this.color.r, this.color.g, this.color.b, this.color.a, 0, 0 // 15
        };
        short[] indices = {
            // Front
            0, 1, 2,
            2, 3, 0,
            // Back
            4, 5, 6,
            6, 7, 4,
            // Left
            8, 9, 10,
            10, 11, 8,
            // Right
            12, 13, 14,
            14, 15, 12,
            // Top
            16, 17, 18,
            18, 19, 16,
            // Bottom
            20, 21, 22,
            22, 23, 20
        };
        this.vertices = vercities;
        this.indices = indices;
        AssetManager assetManager = this.context.getAssets();
        try {
            InputStream stream = assetManager.open(this.textureAssetPath);
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            int textureName[] = new int[1];
            GLES20.glGenTextures(1, textureName, 0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureName[0]);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
            bitmap.recycle();
            this.textureId = textureName[0];
            super.create();
        } catch (IOException e)  {
        }
        return;
    }
    public int getTextureId() {
        return this.textureId;
    }
}
