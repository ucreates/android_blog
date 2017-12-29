// ======================================================================
// Project Name    : sphere
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.collider;
import com.core.math.Distance;
import com.frontend.component.asset.render.model.GLES2BaseModelAsset;
public class SphereCollider extends BaseCollider {
    public SphereCollider(GLES2BaseModelAsset renderAsset) {
        super(renderAsset);
    }
    @Override
    public boolean isIntersected(GLES2BaseModelAsset renderAsset) {
        float opx = this.ownerRenderAsset.transform.position.x;
        float opy = this.ownerRenderAsset.transform.position.y;
        float opz = this.ownerRenderAsset.transform.position.z;
        float oscx = this.ownerRenderAsset.transform.scale.x / 2;
        float oscy = this.ownerRenderAsset.transform.scale.x / 2;
        float oscz = this.ownerRenderAsset.transform.scale.x / 2;
        float apx = renderAsset.transform.position.x;
        float apy = renderAsset.transform.position.y;
        float apz = renderAsset.transform.position.z;
        float ascx = renderAsset.transform.scale.x / 2;
        float ascy = renderAsset.transform.scale.x / 2;
        float ascz = renderAsset.transform.scale.x / 2;
        float bdx = (float)((0.5 * oscx) + (0.5 * ascx));
        float bdy = (float)((0.5 * oscy) + (0.5 * ascy));
        float bdz = (float)((0.5 * oscz) + (0.5 * ascz));
        float cdx = Distance.create(opx, apx);
        float cdy = Distance.create(opy, apy);
        float cdz = Distance.create(opz, apz);
        return (bdx >= cdx && bdy >= cdy && bdz >= cdz);
    }
}
