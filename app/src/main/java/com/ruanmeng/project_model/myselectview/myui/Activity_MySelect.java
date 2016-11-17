package com.ruanmeng.project_model.myselectview.myui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ruanmeng.project_model.MainActivity;
import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.myselectview.customui.SegmentControl;

public class Activity_MySelect extends AppCompatActivity {


    private SegmentControl myselectview;
    private SegmentControl myselectview2;

    String[] texts2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_select);

        initView();

    }

    private void initView() {

        myselectview = (SegmentControl) findViewById(R.id.myselectview);
        myselectview2 = (SegmentControl) findViewById(R.id.myselectview2);

        initData();


        myselectview.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                Toast.makeText(Activity_MySelect.this, index + "", Toast.LENGTH_SHORT).show();

            }
        });

        myselectview2.setText(texts2);
        myselectview2.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                Toast.makeText(Activity_MySelect.this, texts2[index] + "", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initData() {
        texts2 = new String[5];
        for (int i = 0; i < 5; i++) {
            texts2[i] = i + "个";
        }

    }
}
