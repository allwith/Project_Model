package com.ruanmeng.project_model.slideback;

import android.os.Bundle;

import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.slideback.swipeback.SwipeBackActivity;
import com.ruanmeng.project_model.slideback.swipeback.SwipeBackLayout;

public class Activity_TextBack2 extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_back2);

        setDragEdge(SwipeBackLayout.DragEdge.LEFT);

    }
}
