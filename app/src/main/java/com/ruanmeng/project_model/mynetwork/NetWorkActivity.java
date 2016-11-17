package com.ruanmeng.project_model.mynetwork;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;

import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.mynetwork.myrecevier.NetState;
import com.ruanmeng.project_model.mynetwork.netutils.NetWorkUtil;
import com.ruanmeng.project_model.mynetwork.netutils.TipsToast;
import com.ruanmeng.project_model.mynetwork.netutils.UISwitchButton;

import java.lang.reflect.Method;

public class NetWorkActivity extends Activity implements OnClickListener {

	private UISwitchButton switchWifi, switchGprs;
	ConnectivityManager mConnectivityManager;
	ImageView img_gprs, img_wifi;
	private static TipsToast tipsToast;
	Intent intent;
	Button but_close;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_network);
		switchWifi = (UISwitchButton) findViewById(R.id.switch_wifi);
		switchGprs = (UISwitchButton) findViewById(R.id.switch_liuliang);

		img_gprs = (ImageView) findViewById(R.id.img_gprs);
		img_wifi = (ImageView) findViewById(R.id.img_wifi);

		but_close = (Button) findViewById(R.id.but_close);
		but_close.setOnClickListener(this);

		switchWifi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					toggleWiFi(true);
					img_wifi.setBackgroundResource(R.drawable.wuxianlanse);
					showTips(R.drawable.tips_smile, "正在打开WiFi网络...");
				} else {
					toggleWiFi(false);
					img_wifi.setBackgroundResource(R.drawable.wuxian1);
					showTips(R.drawable.tips_smile, "正在关闭WiFi网络...");
				}
			}
		});

		switchGprs.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					setMobileNetEnable();
					img_gprs.setBackgroundResource(R.drawable.wuxianerlans);
					showTips(R.drawable.tips_smile, "正在打开数据网络...");
				} else {
					setMobileNetUnable();
					showTips(R.drawable.tips_smile, "正在关闭数据网络...");
					img_gprs.setBackgroundResource(R.drawable.wuxianer);
				}
			}
		});

	}

	/**
	 * 设置是否启用WIFI网络
	 */
	public void toggleWiFi(boolean status) {
		WifiManager wifiManager = (WifiManager) this
				.getSystemService(Context.WIFI_SERVICE);
		if (status == true && !wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(true);

		} else if (status == false && wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(false);
		}
	}

	/**
	 * 设置启用数据流量
	 */
	public final void setMobileNetEnable() {

		mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		Object[] arg = null;
		try {
			boolean isMobileDataEnable = invokeMethod("getMobileDataEnabled",
					arg);
			if (!isMobileDataEnable) {
				invokeBooleanArgMethod("setMobileDataEnabled", true);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * 设置不启用数据流量
	 */
	public final void setMobileNetUnable() {
		mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		Object[] arg = null;
		try {
			boolean isMobileDataEnable = invokeMethod("getMobileDataEnabled",
					arg);
			if (isMobileDataEnable) {
				invokeBooleanArgMethod("setMobileDataEnabled", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean invokeMethod(String methodName, Object[] arg)
			throws Exception {

		ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		Class ownerClass = mConnectivityManager.getClass();

		Class[] argsClass = null;
		if (arg != null) {
			argsClass = new Class[1];
			argsClass[0] = arg.getClass();
		}

		Method method = ownerClass.getMethod(methodName, argsClass);

		Boolean isOpen = (Boolean) method.invoke(mConnectivityManager, arg);

		return isOpen;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object invokeBooleanArgMethod(String methodName, boolean value)
			throws Exception {

		ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		Class ownerClass = mConnectivityManager.getClass();

		Class[] argsClass = new Class[1];
		argsClass[0] = boolean.class;

		Method method = ownerClass.getMethod(methodName, argsClass);

		return method.invoke(mConnectivityManager, value);
	}

	/**
	 * 自定义taost
	 * 
	 * @param iconResId
	 *            图片
	 * @param
	 *
	 */
	private void showTips(int iconResId, String tips) {
		if (tipsToast != null) {
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
				tipsToast.cancel();
			}
		} else {
			tipsToast = TipsToast.makeText(getApplication().getBaseContext(),
					tips, TipsToast.LENGTH_LONG);
		}
		tipsToast.show();
		tipsToast.setIcon(iconResId);
		tipsToast.setText(tips);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.but_close:// 关闭的同时判断网络状态
			int type = NetWorkUtil.getAPNType(NetWorkActivity.this);
			intent = new Intent();
			intent.putExtra("key", type + ""); // 设置要发送的数据
			setResult(RESULT_OK, intent);
			this.finish();
			break;
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == event.KEYCODE_BACK) {// 返回的同时判断网络状态
			int type = NetWorkUtil.getAPNType(NetWorkActivity.this);
			intent = new Intent();
			intent.putExtra("key", type + ""); // 设置要发送的数据
			setResult(RESULT_OK, intent);
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		NetState.IS_ENABLE = true;// Activity销毁的时候允许启用跳转
	}
}
