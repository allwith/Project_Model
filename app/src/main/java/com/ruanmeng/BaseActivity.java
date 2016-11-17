package com.ruanmeng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ruanmeng.project_model.R;
import com.ruanmeng.utils.ActivityStack;
import com.yolanda.nohttp.rest.Request;

public class BaseActivity extends AppCompatActivity implements
        TextWatcher, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static boolean isHome;
    private Toolbar toolbar;
    public TextView tvBack, tvRight, tvTitle;
    /**
     * 请求对象
     */
    public Request<String> mRequest;
    public Activity baseContext;


    private LinearLayout lay_nowifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        initToolbar();


//       JudgeNet();
        /*判断网络*/
//      lay_nowifi = (LinearLayout) findViewById(R.id.lay_no_net);
//        lay_nowifi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                ReGetData();
//
//            }
//        });


        baseContext = this;

        ActivityStack.getScreenManager().pushActivity(this);
    }


    /**
     * 判断是否有网络
     *
     */
    private NetworkInfo gprs;
    private NetworkInfo wifi;


 /*   private void JudgeNet() {

        ConnectivityManager manager = (ConnectivityManager) baseContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        if (!gprs.isConnected() && !wifi.isConnected()) {

            lay_nowifi.setVisibility(View.VISIBLE);
            lay_nowifi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CommonUtil.showToask(baseContext,"click");
                }
            });

        }

    }*/

    /**
     * 再次获取数据
     */
    private void ReGetData() {


    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
                actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        }
    }

    //初始化控件
    public void init_title() {
        tvBack = (TextView) findViewById(R.id.tv_nav_back);
        tvTitle = (TextView) findViewById(R.id.tv_nav_title);
        tvRight = (TextView) findViewById(R.id.tv_nav_right);
    }

    //改变标题
    public void changeTitle(String title, String name) {
        if (tvTitle == null)
            tvTitle = (TextView) findViewById(R.id.tv_nav_title);
        assert tvTitle != null;
        tvTitle.setText(title);

        if (tvRight == null)
            tvRight = (TextView) findViewById(R.id.tv_nav_right);
        if (name == null) {
            assert tvRight != null;
            tvRight.setVisibility(View.INVISIBLE);
        } else {
            assert tvRight != null;
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(name);
        }
    }

    //设置Toolbar是否可见
    public void setToolbarVisibility(boolean isVisible) {
        if (toolbar != null)
            toolbar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.title_layout);
        if (rootLayout == null)
            return;
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    //标题栏的返回按钮，onclick = "doClick"
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_back:
                onBackPressed();
                break;
        }
    }

    //隐藏键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 切换Activity
     *
     * @param c 需要切换到的Activity
     */
    public void startActivity(Class<?> c) {
        Intent intent = new Intent(this, c);
        this.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出时可以取消这个请求
        if (mRequest != null)
            mRequest.cancel();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    }


    //判空
    public boolean CheckEmpty(String name, String msg) {
        if (name.equals("")) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
