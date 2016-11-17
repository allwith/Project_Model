package com.ruanmeng.project_model.gitview.mycheckbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.gitview.mycheckbox.views.SmoothCheckBox;


// 需要 里边的  attrs
public class MyCheckBox extends AppCompatActivity {
    private TextView tvResult;
    private SmoothCheckBox myCheckbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_check_box);


        initView();
    }

    private void initView() {


        tvResult = (TextView) findViewById(R.id.tv_result);
        myCheckbox = (SmoothCheckBox) findViewById(R.id.my_checkbox);
        myCheckbox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if (isChecked) {
                    tvResult.setText("选择");
                } else {
                    tvResult.setText("取消");
                }
            }
        });

    }
}
