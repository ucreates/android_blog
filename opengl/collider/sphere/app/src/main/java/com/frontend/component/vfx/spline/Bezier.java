// ======================================================================
// Project Name    : sphere
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.vfx.spline;
import android.renderscript.Float3;
public class Bezier {
    public static Float3 create(float currentTime, float totalTime, Float3 start, Float3 control, Float3 end) {
        float t = currentTime / totalTime;
        if (t > 1.0) {
            t = 1.0f;
        }
        float tp = 1.0f - t;
        if (tp < 0.0f) {
            tp = 0.0f;
        }
        float x = t * t * end.x + 2 * t * tp * control.x + tp * tp * start.x;
        float y = t * t * end.y + 2 * t * tp * control.y + tp * tp * start.y;
        float z = t * t * end.z + 2 * t * tp * control.z + tp * tp * start.z;
        return new Float3(x, y, z);
    }
    public static Float3 create(float currentTime, float totalTime, Float3 start, Float3 control1, Float3 control2, Float3 end) {
        float t = currentTime / totalTime;
        if (t > 1.0) {
            t = 1.0f;
        }
        float tp = 1.0f - t;
        if (tp < 0.0f) {
            tp = 0.0f;
        }
        float x = t * t * t * end.x + 3 * t * t * tp * control2.x + 3 * t * tp * tp * control1.x + tp * tp * tp * start.x;
        float y = t * t * t * end.y + 3 * t * t * tp * control2.y + 3 * t * tp * tp * control1.y + tp * tp * tp * start.y;
        float z = t * t * t * end.z + 3 * t * t * tp * control2.z + 3 * t * tp * tp * control1.z + tp * tp * tp * start.z;
        return new Float3(x, y, z);
    }
}
