// ======================================================================
// Project Name    : box
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.activity;
import android.os.Bundle;
import android.renderscript.Float3;
import android.support.v7.app.AppCompatActivity;
import com.frontend.behaviour.BaseBehaviour;
import com.frontend.behaviour.RedTextureBehaviour;
import com.frontend.behaviour.BlueTextureBehaviour;
import com.frontend.view.GLES2View;
import com.ucreates.blog.collider.box.R;
import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseBehaviour behaviour1 = new RedTextureBehaviour(this);
        BaseBehaviour behaviour2 = new BlueTextureBehaviour(this);
        Map<Integer, BaseBehaviour> behaviourMap = new HashMap<>();
        behaviourMap.put(behaviour1.id, behaviour1);
        behaviourMap.put(behaviour2.id, behaviour2);
        GLES2View view = (GLES2View)this.findViewById(R.id.gles2view);
        view.create(behaviourMap, new Float3(0.0f, 0.0f, -5.0f), 100f, 1.0f, 150.0f);
        return;
    }
}
