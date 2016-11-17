package com.ruanmeng.project_model.slideback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.slideback.swipeback.SwipeBackActivity;
import com.ruanmeng.project_model.slideback.swipeback.SwipeBackLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity_TextBack extends SwipeBackActivity {


    @BindView(R.id.btn_next_back)
    Button btnNextBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_back);
        ButterKnife.bind(this);
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);


        setTitle("侧滑退出");


//        tobar.setTitleTextColor(Color.WHITE);

        btnNextBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_TextBack.this, Activity_TextBack2.class));
            }
        });

    }
}
