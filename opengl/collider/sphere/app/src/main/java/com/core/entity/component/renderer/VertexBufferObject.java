// ======================================================================
// Project Name    : sphere
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
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
public class VertexBufferObject {
    public static final int COORDS_PER_VERTEX = 3;
    public static final int COLORS_PER_VERTEX = 4;
    public static final int UV_PER_VERTEX = 2;
    public static final int POSITION_START_POINTER = 0;
    public static final int COLOR_START_POINTER = VertexBufferObject.COORDS_PER_VERTEX * MemoryLayout.GL_FLOAT_SIZE;
    public static final int UV_START_POINTER = (VertexBufferObject.COORDS_PER_VERTEX + VertexBufferObject.COLORS_PER_VERTEX) * MemoryLayout.GL_FLOAT_SIZE;
    public int id;
    public int stride;
    public void create(float vertices[]) {
        int vertexSize = MemoryLayout.GL_FLOAT_SIZE;
        int dataSize = vertices.length * vertexSize;
        ByteBuffer buffer = Allocator.allocate(dataSize);
        FloatBuffer floatBuffer = buffer.asFloatBuffer();
        floatBuffer.put(vertices);
        floatBuffer.position(0);
        IntBuffer intBuffer = IntBuffer.allocate(1);
        GLES20.glGenBuffers(1, intBuffer);
        this.id = intBuffer.get(0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.id);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, dataSize, floatBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glEnableVertexAttribArray(Vertex.Attributes.POSITION);
        GLES20.glVertexAttribPointer(Vertex.Attributes.POSITION, VertexBufferObject.COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexSize, VertexBufferObject.POSITION_START_POINTER);
        GLES20.glEnableVertexAttribArray(Vertex.Attributes.COLOR);
        GLES20.glVertexAttribPointer(Vertex.Attributes.COLOR, VertexBufferObject.COLORS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexSize, VertexBufferObject.COLOR_START_POINTER);
        GLES20.glEnableVertexAttribArray(Vertex.Attributes.TEXCOORD);
        GLES20.glVertexAttribPointer(Vertex.Attributes.TEXCOORD, VertexBufferObject.UV_PER_VERTEX, GLES20.GL_FLOAT, false, vertexSize, VertexBufferObject.UV_START_POINTER);
        this.stride = (VertexBufferObject.COORDS_PER_VERTEX + VertexBufferObject.COLORS_PER_VERTEX + VertexBufferObject.UV_PER_VERTEX) * MemoryLayout.GL_FLOAT_SIZE;
        return;
    }
}
