package com.ruanmeng.project_model.mynetwork;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.mynetwork.myrecevier.NetState;
import com.ruanmeng.project_model.mynetwork.netutils.NetWorkUtil;
import com.ruanmeng.project_model.mynetwork.netutils.TipsToast;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity_NetText1 extends com.ruanmeng.BaseActivity {

    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.btn_do)
    Button btnDo;
    @BindView(R.id.img_text)
    ImageView imgText;

    private final String img_url = "http://img.taopic.com/uploads/allimg/130711/318756-130G1222R317.jpg";

    private Activity basecontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_text1);
        ButterKnife.bind(this);
        basecontext = this;
        initNetState();

        inittv();


        getImg();


    }

    private void getImg() {

//        Picasso.get
        Picasso.with(Activity_NetText1.this).load(img_url).placeholder(R.drawable.zanwudingdan).error(R.drawable.error_image).into(imgText);

    }

    private void inittv() {

        btnDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(basecontext, Activity_NetText2.class));
//                shownothing(Activity_NetText1.this,"context");

            }
        });


        int type = NetWorkUtil.getAPNType(Activity_NetText1.this);


        if ("-1".equals(type + "")) {
            tvState.setText("当前网络不可用");
        } else if ("2".equals(type + "")) {
            tvState.setText("当前连接网络为wap网络");
        } else if ("3".equals(type + "")) {
            tvState.setText("当前连接网络为net网络");
        } else if ("1".equals(type + "")) {
            tvState.setText("当前连接网络为WiFi");
        }
    }
    public  void  shownothing(Context context, String s)
    {

        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();

//        context  实现 activity  的功能
        (  (Activity)context).finish();

    }
    //    初始化 广播接收器
    private void initNetState() {

// 注册广播接收器Start
        NetState receiver = new NetState();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(receiver, filter);
        receiver.onReceive(this, null);
        // 注册广播接收器End
    }


    private static TipsToast tipsToast;

    /**
     * 自定义taost
     *
     * @param iconResId 图片
     * @param
     */
    public void showTips(int iconResId, String tips) {
        if (tipsToast != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                tipsToast.cancel();
            }
        } else {
            tipsToast = TipsToast.makeText(getApplication().getBaseContext(),
                    tips, TipsToast.LENGTH_SHORT);
        }
        tipsToast.show();
        tipsToast.setIcon(iconResId);
        tipsToast.setText(tips);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {// 根据反馈值判断当前网络状态
            case 1:
                String keyStr = data.getStringExtra("key");
                System.out.println("--------- onActivityResult -------keyStr="
                        + keyStr);
                if ("-1".equals(keyStr)) {
                    showTips(R.drawable.tips_error, "网络不可用...");
                    tvState.setText("当前网络不可用");
                } else {
                    showTips(R.drawable.tips_smile, "网络已恢复正常...");
                    if ("1".equals(keyStr)) {
                        tvState.setText("当前连接网络为WiFi");
                    } else if ("2".equals(keyStr)) {
                        tvState.setText("当前连接网络为wap网络");
                    } else if ("3".equals(keyStr)) {
                        tvState.setText("当前连接网络为net网络");
                    }

                }

                break;
        }
    }


}
