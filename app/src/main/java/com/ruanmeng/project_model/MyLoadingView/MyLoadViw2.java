package com.ruanmeng.project_model.MyLoadingView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.handmark.pulltorefresh.library.internal.RotateLoadingLayout;
import com.ruanmeng.project_model.R;

public class MyLoadViw2 extends AppCompatActivity {
    private RotateLoadingLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_load_viw2);

        initView();
    }

    private void initView() {

        loading = (RotateLoadingLayout) findViewById(R.id.loading);

//        loading.setVisibility(View.GONE);
//        loading.cancelDragAndDrop

    }
}
