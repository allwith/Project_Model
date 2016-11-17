package com.ruanmeng.project_model.mynetwork.myrecevier;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.ruanmeng.project_model.mynetwork.NetWorkActivity;

/**
 * 网络状态广播接收器
 *
 * @author zihao
 */
public class NetState extends BroadcastReceiver {

    public static boolean IS_ENABLE = true;
    private NetworkInfo gprs;
    private NetworkInfo wifi;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        // 监听网络状态
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        startActivity(context);

    }

    /**
     * 无网络进行跳转
     * 如果有网络了  如何通知整个app 每个页面
     *
     * @param context
     */
    private void startActivity(Context context) {

        if (!gprs.isConnected() && !wifi.isConnected() && IS_ENABLE) {
            System.out.println("start");
            IS_ENABLE = false;
//			((MainActivity) context).showTips(R.drawable.tips_error,
//					"网络未连接，请先连接网络...");
            Intent intent = new Intent().setClass(context,
                    NetWorkActivity.class);
            ((Activity) context).startActivityForResult(intent, 1);
        } else if (gprs.isConnected() || wifi.isConnected()) {
            String str_text = "网络连接成功";
            if (gprs.isConnected()) {
                str_text = "gprs 连接成功";
            } else if (wifi.isConnected()) {
                str_text = "wifi 连接成功";
            }
            Toast.makeText(context, str_text, Toast.LENGTH_SHORT).show();
        }

    }

}