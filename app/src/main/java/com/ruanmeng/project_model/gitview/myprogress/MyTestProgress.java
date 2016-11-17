package com.ruanmeng.project_model.gitview.myprogress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ruanmeng.project_model.R;
import com.ruanmeng.utils.CommonUtil;

public class MyTestProgress extends AppCompatActivity {


    // TODO: 2016/10/17     两个自定义的属性  用于 使proressbar 变成垂直
//   progress2
// progress_vertical_gradient_simple_shape.xml


    //    彩色
    private ProgressBar pbVerticalSimpleShape;
    private Button mBtnAdd;
    private Button mBtnSub;


    //     黑白色
    private Button btnMyAdd;
    private ProgressBar pbMyview;
    private Button btnMySub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test_progress);


        initView1();
        initView2();
    }

    private void initView2() {
        pbVerticalSimpleShape = (ProgressBar) findViewById(R.id.pb_vertical_simple_shape);
        pbVerticalSimpleShape.setProgress(50);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnSub = (Button) findViewById(R.id.btn_sub);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbVerticalSimpleShape.setProgress(pbVerticalSimpleShape.getProgress() + 5);
//                CircularAnimUtil.show(mBtnAdd);
                CommonUtil.showToask(MyTestProgress.this, pbVerticalSimpleShape.getProgress() + "");
            }
        });


        mBtnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pbVerticalSimpleShape.setProgress(pbVerticalSimpleShape.getProgress() - 5);
//                CircularAnimUtil.hide(mBtnAdd);
                CommonUtil.showToask(MyTestProgress.this, pbVerticalSimpleShape.getProgress() + "");

            }
        });
    }

    private void initView1() {


        btnMyAdd = (Button) findViewById(R.id.btn_my_add);
        pbMyview = (ProgressBar) findViewById(R.id.pb_myview);
        btnMySub = (Button) findViewById(R.id.btn_my_sub);
        pbMyview.setProgress(20);

        btnMyAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbMyview.setProgress(pbMyview.getProgress() + 5);
                CommonUtil.showToask(MyTestProgress.this, pbMyview.getProgress() + "");

            }
        });


        btnMySub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pbMyview.setProgress(pbMyview.getProgress() - 5);
                CommonUtil.showToask(MyTestProgress.this, pbMyview.getProgress() + "");

            }
        });
    }

    @Override
    public void finish() {
        super.finish();

//        overridePendingTransition(R.anim.anim_none, R.anim.trans_center_2_right);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }
}
