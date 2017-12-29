// ======================================================================
// Project Name    : box
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.core.math;
public class Distance {
    public static float create(float start, float end) {
        float distance = Math.abs(start - end);
        distance = (float)Math.pow(distance, 2);
        return (float)Math.sqrt(distance);
    }
}
