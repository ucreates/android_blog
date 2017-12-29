// ======================================================================
// Project Name    : cube
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.behaviour;
import android.content.Context;
import com.core.timer.TimeInterval;
import com.frontend.component.asset.render.model.BaseModelAsset;
import com.frontend.component.renderer.BaseRenderer;
public abstract class BaseBehaviour {
    protected int id;
    protected Context context;
    protected BaseRenderer renderer;
    protected BaseModelAsset renderAsset;
    public BaseBehaviour(Context context) {
        this.context = context;
    }
    public void onCreate() {
        return;
    }
    public void onUpdate(TimeInterval delta) {
        return;
    }
    public void onRender() {
        this.renderer.render(this.renderAsset);
        return;
    }
    public BaseModelAsset getRenderAsset() {
        return this.renderAsset;
    }
}
