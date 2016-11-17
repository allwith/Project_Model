package com.ruanmeng.project_model.cityselector;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.ruanmeng.utils.CommonUtil;
import com.yolanda.nohttp.demo.HttpListener;
import com.yolanda.nohttp.rest.Response;

import android.content.Context;
import android.util.Log;

public abstract class CityHttpListener implements HttpListener<String> {

	private JSONObject object;
	private Context context;
	private boolean isGson;
	private Class<?> dataM;

	public CityHttpListener(Context context, boolean isGson, Class<?> dataM) {
		this.context = context;
		this.isGson = isGson;
		this.dataM = dataM;
	}

	@Override
	public void onSucceed(int what, Response<String> response) {
		Log.i("onSucceed", "请求成功：\n" + response.get());
		try {
			object = new JSONObject(response.get());
			if(dataM == null && "0".equals(object.getString("msgcode"))) {
				CommonUtil.showToask(context, object.getString("msg"));
				return;
			}
			if(!"0".equals(object.getString("msgcode"))) {
				if(isGson && dataM != null) {
					Gson gson = new Gson();
					doWork(gson.fromJson(object.toString(), dataM), "1");
				} else {
					if("1".equals(object.getString("msgcode")))
						if (!object.isNull("data"))
							doWork(((JSONObject) object).getJSONObject("data"), "1");
						else
							doWork(object, "1");
					if("2".equals(object.getString("msgcode")))
						doWork(((JSONObject) object).getJSONObject("data"), "2");
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			try {
				if(!isGson && !"0".equals(object.getString("msgcode"))) {
					CommonUtil.showToask(context, object.getString("msg"));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			onFinally(object);
		}
	}

	public abstract void doWork(Object data, String code);

	public abstract void onFinally(JSONObject obj);

	@Override
	public void onFailed(int what, String url, Object tag, Exception e, int responseCode, long networkMillis) {
		Log.i("onFailed", "请求失败：\n" + e.getMessage());
		CommonUtil.showToask(context, "请求数据失败");
	}

}
