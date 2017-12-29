// ======================================================================
// Project Name    : box
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.collider;
import com.frontend.component.asset.render.model.GLES2BaseModelAsset;
public abstract class BaseCollider {
    protected GLES2BaseModelAsset ownerRenderAsset;
    public BaseCollider(GLES2BaseModelAsset renderAsset) {
        this.ownerRenderAsset = renderAsset;
    }
    public boolean isIntersected(GLES2BaseModelAsset renderAsset) {
        return true;
    }
}
