package com.ruanmeng.project_model.mylockview.myui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ruanmeng.project_model.R;
import com.ruanmeng.utils.CommonUtil;

public class MyDetailsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details_view);


    }

    public void DoMyClick(View v){

        switch (v.getId()){
            case  R.id.btn_show:
                CommonUtil.showToask(MyDetailsView.this,"欢迎来到德莱联盟！");
                break;
        }

    }
}
