package com.ruanmeng.project_model.mylockview.myui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

import com.ruanmeng.project_model.R;

import java.util.Timer;
import java.util.TimerTask;

public class MyFail_Activity extends Activity {
    private TextView mTvMyMsg;
    private TextView mTvNums;

    private long myTimers = 30;


    public long getMyTimers() {
        return myTimers;
    }

    public void setMyTimers(long myTimers) {
        this.myTimers = myTimers;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            /*set it to be no title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);

//        /*set it to be full screen*/
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_my_fail);

//        //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


        initView();
        BegancountDown();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//        long

            if (msg.what == 100) {
                myTimers--;
                mTvNums.setText(myTimers+"");
                while (myTimers <= 0) {
                    timer.cancel();
                    myTimers = 30;
                    MyFail_Activity.this.finish();
                }
            }


        }
    };

    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(100);
        }
    };

    private void BegancountDown() {
        timer.schedule(timerTask, 1000, 1000);

    }

    private void initView() {


        mTvMyMsg = (TextView) findViewById(R.id.tv_my_msg);
        mTvNums = (TextView) findViewById(R.id.tv_nums);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
// TODO: 2016/10/14   不做处理  使点击回退按钮 无效

        }

        return false;
    }
}
