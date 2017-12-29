// ======================================================================
// Project Name    : cube
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.core.entity.component.renderer;
import android.opengl.GLES20;
import com.core.io.memory.Allocator;
import com.core.io.memory.MemoryLayout;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
public class IndexBufferObject {
    public int id;
    public void create(short indicies[]) {
        ByteBuffer buffer = Allocator.allocate(indicies.length * MemoryLayout.GL_FLOAT_SIZE);
        ShortBuffer shortBuffer = buffer.asShortBuffer();
        shortBuffer.put(indicies);
        shortBuffer.position(0);
        IntBuffer intBuffer = IntBuffer.allocate(1);
        GLES20.glGenBuffers(1, intBuffer);
        this.id = intBuffer.get(0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.id);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, indicies.length * MemoryLayout.GL_SHORT_SIZE, shortBuffer, GLES20.GL_STATIC_DRAW);
        return;
    }
}
