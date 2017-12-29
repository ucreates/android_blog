// ======================================================================
// Project Name    : sphere
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
import com.frontend.component.asset.render.model.GLES2BaseModelAsset;
import com.frontend.component.collider.BaseCollider;
import com.frontend.component.renderer.BaseRenderer;
public abstract class BaseBehaviour {
    public int id;
    public boolean destroy;
    public boolean intersect;
    public int intersectedBehaviourId;
    public BaseCollider collider;
    public GLES2BaseModelAsset renderAsset;
    protected Context context;
    protected BaseRenderer renderer;
    public BaseBehaviour(Context context) {
        this.context = context;
        this.collider = null;
        this.destroy = false;
        this.intersect = false;
        this.intersectedBehaviourId = -1;
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
    public void onCollisionEnter(BaseBehaviour behaviour) {
        return;
    }
    public void onCollisionStay(BaseBehaviour behaviour) {
        return;
    }
    public void onCollisionExit(BaseBehaviour behaviour) {
        return;
    }
}
