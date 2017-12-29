// ======================================================================
// Project Name    : behaviour
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.core.io.memory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
public class Allocator {
    public static ByteBuffer allocate(int count) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(count);
        ByteOrder nativeOrder = ByteOrder.nativeOrder();
        buffer.order(nativeOrder);
        return buffer;
    }
}
