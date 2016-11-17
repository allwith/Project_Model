package com.ruanmeng.project_model.mylockview.myui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.mylockview.lockui.GestureEditActivity;
import com.ruanmeng.project_model.mylockview.lockui.GestureVerifyActivity;
import com.ruanmeng.project_model.mylockview.lockuitls.SPUtils;
import com.ruanmeng.utils.CommonUtil;

public class MyOperation_Activity extends AppCompatActivity {
    private Button mBtnNext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_operation);
//        gesturePsd
        initView();

    }

    private void initView() {
        mBtnNext = (Button) findViewById(R.id.btn_next);



        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String str_name= (String) SPUtils.get(MyOperation_Activity.this, "username", "");
              String str_psd= (String) SPUtils.get(MyOperation_Activity.this, "userpsd", "");
                if (TextUtils.isEmpty(str_name) || TextUtils.isEmpty(str_psd)) {
                    CommonUtil.showToask(MyOperation_Activity.this,"请先登录");
                    startActivity(new Intent(MyOperation_Activity.this,MyForget_activity.class));
                    return;
//                    return;
                }

                if (!SPUtils.get(MyOperation_Activity.this, "MyGesturePsd", "").toString().equals("")) {
                    startActivity(new Intent(MyOperation_Activity.this, GestureVerifyActivity.class));
                }else
                {
                    startActivity(new Intent(MyOperation_Activity.this, GestureEditActivity.class));
                }
                finish();
            }
        });



    }
}
