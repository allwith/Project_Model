package com.ruanmeng.project_model.lunbo;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.ruanmeng.nohttp.HttpListener;
import com.ruanmeng.utils.CommonUtil;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by 小卷毛 on 2016/4/28 0028.
 */
public abstract class CustomHttpListener4 implements HttpListener<String> {

	private JSONObject object;
	private Context context;
	private boolean isGson;
	private Class<?> dataM;

	public CustomHttpListener4(Context context, boolean isGson, Class<?> dataM) {
		this.context = context;
		this.isGson = isGson;
		this.dataM = dataM;
	}

	@Override
	public void onSucceed(int what, Response<String> response) {
		Log.i("onSucceed", "请求成功：\n" + response.get());
		try {
			object = new JSONObject(response.get());
//			Gson gson = new Gson();
//			if(dataM == null && "0".equals(object.getString("msgcode"))) {
//				CommonUtil.showToask(context, object.getString("msg"));
//				return;
//			}
			if(dataM == null) {
				CommonUtil.showToask(context, object.getString("msg"));
				return;
			}
			if("0".equals(object.getString("code"))) {
				if(isGson && dataM != null) {
					Gson gson = new Gson();
					doWork(gson.fromJson(object.toString(), dataM), true);
					Log.i("isGo","true");
				} else {
					if("1".equals(object.getString("code")))
						if (!object.isNull("data"))
							doWork(((JSONObject) object).getJSONObject("data"), true);
						else
							doWork(object, true);
					if("2".equals(object.getString("code")))
						doWork(((JSONObject) object).getJSONObject("data"), false);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			try {
				if(!isGson) {
					CommonUtil.showToask(context, object.getString("msg"));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			onFinally(object);
		}
	}

	public abstract void doWork(Object data, boolean isOne);

	public void onFinally(JSONObject obj){}; //解析完成，如要执行操作，可重写该方法。

	@Override
	public void onFailed(int what, String url, Object tag, Exception e, int responseCode, long networkMillis) {
		Log.i("onFailed", "请求失败：\n" + e.getMessage());
		CommonUtil.showToask(context, "请求数据失败");
	}

}
