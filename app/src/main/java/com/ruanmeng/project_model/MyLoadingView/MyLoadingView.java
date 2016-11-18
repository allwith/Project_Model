package com.ruanmeng.project_model.MyLoadingView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ruanmeng.project_model.MyLoadingView.views.LoadingView;
import com.ruanmeng.project_model.R;

public class MyLoadingView extends AppCompatActivity {
    private Button btn123;
    private Button btn111;
    private LoadingView loadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_view);


        initView();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            loadView.setVisibility(View.GONE);
//            loadView.st

        }
    };

    private void initView() {


        btn123 = (Button) findViewById(R.id.btn123);
        btn123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadView.setVisibility(View.VISIBLE);
//                handler.sendEmptyMessage(1);
//                handler.sendEmptyMessageDelayed(1,2000);
            }
        });


        btn111 = (Button) findViewById(R.id.btn111);
        btn111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadView.setVisibility(View.GONE);
                loadView.setVisibility(View.GONE);


            }
        });
        loadView = (LoadingView) findViewById(R.id.loadView);


    }
}
