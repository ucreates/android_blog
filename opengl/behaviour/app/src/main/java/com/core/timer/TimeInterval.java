// ======================================================================
// Project Name    : behaviour
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.core.timer;
public class TimeInterval {
    private static TimeInterval instance = null;
    private long previewTime;
    private float delta;
    private TimeInterval() {
        this.previewTime = 0;
        this.delta = 0;
    }
    public static TimeInterval getInstance() {
        if (null == TimeInterval.instance) {
            TimeInterval.instance = new TimeInterval();
        }
        return TimeInterval.instance;
    }
    public void update() {
        long currentTime = System.currentTimeMillis();
        float diff = currentTime - this.previewTime;
        this.delta = diff * 0.001f;
        this.previewTime = currentTime;
    }
    public float getDelta() {
        return this.delta;
    }
}
