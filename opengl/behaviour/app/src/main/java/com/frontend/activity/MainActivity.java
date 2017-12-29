// ======================================================================
// Project Name    : behaviour
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
import com.frontend.behaviour.BlueTextureBehaviour;
import com.frontend.behaviour.RedTextureBehaviour;
import com.frontend.view.GLES2View;
import com.ucreates.blog.behaviour.R;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<BaseBehaviour> behaviourList = new ArrayList<BaseBehaviour>();
        behaviourList.add(new RedTextureBehaviour(this));
        behaviourList.add(new BlueTextureBehaviour(this));
        GLES2View view = (GLES2View)this.findViewById(R.id.gles2view);
        view.create(behaviourList, new Float3(0.0f, 0.0f, -5.0f), 100f, 1.0f, 150.0f);
        return;
    }
}
