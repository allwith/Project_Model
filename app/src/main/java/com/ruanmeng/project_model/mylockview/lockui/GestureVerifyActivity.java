package com.ruanmeng.project_model.mylockview.lockui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruanmeng.Application;
import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.mylockview.myui.MyFail_Activity;
import com.ruanmeng.project_model.mylockview.lockuitls.ActivityCollector;
import com.ruanmeng.project_model.mylockview.lockuitls.SPUtils;
import com.ruanmeng.project_model.mylockview.myui.MyDetailsView;
import com.ruanmeng.project_model.mylockview.myui.MyForget_activity;
import com.ruanmeng.project_model.mylockview.widget.GestureContentView;
import com.ruanmeng.project_model.mylockview.widget.GestureDrawline;


/**
 * 手势绘制/校验界面
 */
public class GestureVerifyActivity extends Activity implements
        View.OnClickListener {
    /**
     * 手机号码
     */
    public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
    /**
     * 意图
     */
    public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";


    private RelativeLayout mTopLayout;
    private TextView mTextTitle;
    private TextView mTextCancel;
    private ImageView mImgUserLogo;
    private TextView mTextPhoneNumber;
    private TextView mTextTip;
    private FrameLayout mGestureContainer;
    private GestureContentView mGestureContentView;
    private TextView mTextForget;
    // private TextView mTextOther;
    private String mParamPhoneNumber;
    private long mExitTime = 0;
    private int mParamIntentCode;

    private String getGpsd = "";

    private String nameString, psdString;
    private boolean isbackground = false;
    private int psd_count = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_verify);

        getGpsd = SPUtils.get(Application.getContext(), "MyGesturePsd", "")
                .toString();

        nameString = SPUtils.get(Application.getContext(), "username",
                "username").toString();

        isbackground = (Boolean) SPUtils.get(Application.getContext(),
                "isbackground", false);

        ActivityCollector.addActivity(this);
        ObtainExtraData();
        setUpViews();
        setUpListeners();
    }

    private void ObtainExtraData() {
        mParamPhoneNumber = getIntent().getStringExtra(PARAM_PHONE_NUMBER);
        mParamIntentCode = getIntent().getIntExtra(PARAM_INTENT_CODE, 0);
    }

    private void setUpViews() {
        mTopLayout = (RelativeLayout) findViewById(R.id.top_layout);
        mTextTitle = (TextView) findViewById(R.id.text_title);
        mTextCancel = (TextView) findViewById(R.id.text_cancel);
        mImgUserLogo = (ImageView) findViewById(R.id.user_logo);
        mTextPhoneNumber = (TextView) findViewById(R.id.text_phone_number);
        mTextTip = (TextView) findViewById(R.id.text_tip);
        mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
        mTextForget = (TextView) findViewById(R.id.text_forget_gesture);
        // mTextOther = (TextView) findViewById(R.id.text_other_account);

        mTextPhoneNumber.setText(nameString);
        mImgUserLogo.setImageResource(R.mipmap.ic_launcher);
        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, true, getGpsd,
                new GestureDrawline.GestureCallBack() {

                    @Override
                    public void onGestureCodeInput(String inputCode) {

                    }

                    @Override
                    public void checkedSuccess() {
                        mGestureContentView.clearDrawlineState(0L);

                        if (isbackground) {
                            SPUtils.put(Application.getContext(),
                                    "isbackground", false);

                            GestureVerifyActivity.this.finish();

                        } else {
                            Intent intent = new Intent();
                            intent.setClass(getApplicationContext(),
                                    MyDetailsView.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void checkedFail() {

                        psd_count--;
                        if (psd_count <= 0) {
                            mGestureContentView.clearDrawlineState(0L);


// TODO: 2016/10/13      输入错误超过五次之后 的操作处理
                            SPUtils.remove(Application.getContext(),
                                    "MyGesturePsd");
                            mTextTip.setVisibility(View.GONE);

                            Intent intent = new Intent(
                                    GestureVerifyActivity.this,
                                    MyFail_Activity.class);
                            // 打开新的Activity
                            startActivity(intent);
//                            psd_count = 5;
// finish();

                        }

                        mGestureContentView.clearDrawlineState(800L);
                        mTextTip.setVisibility(View.VISIBLE);
                        mTextTip.setText(Html
                                .fromHtml("<font color='#c70c1e'>密码错误,还可再试</font>"
                                        + "<font color='#c70c1e'>"
                                        + psd_count
                                        + "</font>"
                                        + "<font color='#c70c1e'>次</font>"));

                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils
                                .loadAnimation(GestureVerifyActivity.this,
                                        R.anim.shake);
                        mTextTip.startAnimation(shakeAnimation);
                    }
                });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
    }

    private void setUpListeners() {
        mTextCancel.setOnClickListener(this);
        mTextForget.setOnClickListener(this);
        // mTextOther.setOnClickListener(this);
    }

    private String getProtectedMobile(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
            return "";

        }
        StringBuilder builder = new StringBuilder();
        builder.append(phoneNumber.subSequence(0, 3));
        builder.append("****");
        builder.append(phoneNumber.subSequence(7, 11));
        return builder.toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_cancel:
                this.finish();
                break;
            case R.id.text_forget_gesture:

                SPUtils.clear(Application.getContext());
                Intent intent = new Intent(GestureVerifyActivity.this,
                        MyForget_activity.class);
                // 打开新的Activity
                startActivity(intent);
                finish();

                break;

            default:
                break;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
//                System.exit(0);

                GestureVerifyActivity.this.finish();

                return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (psd_count<=0){
            psd_count=5;
        }


        if (psd_count == 5) {
            mTextTip.setVisibility(View.GONE);
        }



    }
}
