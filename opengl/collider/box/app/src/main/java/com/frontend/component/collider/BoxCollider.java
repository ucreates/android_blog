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
public class BoxCollider extends BaseCollider {
    public BoxCollider(GLES2BaseModelAsset renderAsset) {
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
        float osx = opx - oscx;
        float oex = opx + oscx;
        float osy = opy - oscy;
        float oey = opy + oscy;
        float osz = opz - oscz;
        float oez = opz + oscz;
        float asx = apx - ascx;
        float aex = apx + ascx;
        float asy = apy - ascy;
        float aey = apy + ascy;
        float asz = apz - ascz;
        float aez = apz + ascz;
        return ((osx <= asx && asx <= oex && osy <= asy && asy <= oey && osz <= asz && asz <= oez) || (osx <= aex && aex <= oex && osy <= aey && aey <= oey && osz <= aez && aez <= oez));
    }
}
