package com.ruanmeng.project_model.cityselector;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.mycitylist.ui.MyCityList;
import com.ruanmeng.share.Const;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.demo.CallServer;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

public class SelectActivity extends BaseActivity {

    private PopupWindow popupWindow;
    private WheelView wv_province, wv_city, wv_country;

    private List<CityData.CityInfo> list_province = new ArrayList<>();
    private List<CityData.CityInfo> list_city = new ArrayList<>();
    private List<CityData.CityInfo> list_country = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        init_title();
        changeTitle("选择城市", null);
    }

    @Override
    public void doClick(View v) {
        super.doClick(v);
        switch (v.getId()) {
            case R.id.button4:
                startActivity(AreaActivity.class);
                break;
            case R.id.button5:
                mRequest = NoHttp.createStringRequest("http://122.114.101.135:8663/app/area", Const.GET);
                // 添加到请求队列
                CallServer.getRequestInstance().add(this, 0, mRequest,
                        new CityHttpListener(baseContext, true, CityData.class) {
                            @Override
                            public void onFinally(JSONObject obj) {
                            }

                            @Override
                            public void doWork(Object data, String  isOne) {
                                list_province.clear();
                                list_province.addAll(((CityData) data).getData());
                                getCityData(list_province.get(0).getProvinceCode());
                            }
                        }, true, true);
                break;
            case R.id.button6:
                startActivity(MyCityList.class);
                break;
        }
    }

    private void getCityData(String provinceCode) {
        mRequest = NoHttp.createStringRequest("http://122.114.101.135:8663/app/area/getCityList", Const.GET);
        mRequest.add("provinceCode", provinceCode);
        // 添加到请求队列
        CallServer.getRequestInstance().add(this, 0, mRequest,
                new CityHttpListener(baseContext, true, CityData.class) {
                    @Override
                    public void onFinally(JSONObject obj) {
                    }

                    @Override
                    public void doWork(Object data, String isOne) {
                        list_city.clear();
                        list_city.addAll(((CityData) data).getData());
                        if (wv_country != null) {
                            List<String> str_shi = new ArrayList<String>();
                            for (CityData.CityInfo info : list_city)
                                str_shi.add(info.getCityName());
                            wv_city.setViewAdapter(new ArrayWheelAdapter<>(baseContext,
                                    str_shi.toArray(new String[str_shi.size()])));
                            wv_city.setCurrentItem(0);
                        }
                        getCountryData(list_city.get(0).getCityCode());
                    }
                }, true, true);
    }

    private void getCountryData(String cityCode) {
        mRequest = NoHttp.createStringRequest("http://122.114.101.135:8663/app/area/getCountyList", Const.GET);
        mRequest.add("cityCode", cityCode);
        // 添加到请求队列
        CallServer.getRequestInstance().add(this, 0, mRequest,
                new CityHttpListener(baseContext, true, CityData.class) {
                    @Override
                    public void onFinally(JSONObject obj) {
                    }

                    @Override
                    public void doWork(Object data, String  isOne) {
                        list_country.clear();
                        list_country.addAll(((CityData) data).getData());
                        if (wv_country != null) {
                            List<String> str_qu = new ArrayList<String>();
                            for (CityData.CityInfo info : list_country)
                                str_qu.add(info.getCountyName());
                            wv_country.setViewAdapter(new ArrayWheelAdapter<>(baseContext,
                                    str_qu.toArray(new String[str_qu.size()])));
                            wv_country.setCurrentItem(0);
                        } else {
                            initPopWindow(R.layout.popmenu_city_edit);
                        }
                    }
                }, true, true);
    }

    private void initPopWindow(int resId) {
        View view = LayoutInflater.from(this).inflate(resId, null);
        wv_province = (WheelView) view.findViewById(R.id.wv_city_popu_sheng);
        wv_city = (WheelView) view.findViewById(R.id.wv_city_popu_shi);
        wv_country = (WheelView) view.findViewById(R.id.wv_city_popu_qu);
        TextView tv_cancle = (TextView) view.findViewById(R.id.tv_city_popu_cancle);
        TextView tv_sure = (TextView) view.findViewById(R.id.tv_city_popu_sure);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv_country = null;
                popupWindow.dismiss();
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                wv_country = null;
            }
        });

        List<String> str_sheng = new ArrayList<String>();
        List<String> str_shi = new ArrayList<String>();
        List<String> str_qu = new ArrayList<String>();
        for (CityData.CityInfo info : list_province)
            str_sheng.add(info.getProvinceName());
        for (CityData.CityInfo info : list_city)
            str_shi.add(info.getCityName());
        for (CityData.CityInfo info : list_country)
            str_qu.add(info.getCountyName());

        wv_province.setViewAdapter(
                new ArrayWheelAdapter<>(this, str_sheng.toArray(new String[str_sheng.size()])));
        wv_city.setViewAdapter(new ArrayWheelAdapter<>(this, str_shi.toArray(new String[str_shi.size()])));
        wv_country.setViewAdapter(new ArrayWheelAdapter<>(this, str_qu.toArray(new String[str_qu.size()])));

        wv_province.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                getCityData(list_province.get(wv_province.getCurrentItem()).getProvinceCode());
            }
        });
        wv_city.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                getCountryData(list_city.get(wv_city.getCurrentItem()).getCityCode());
            }
        });
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        // 必须要有这句否则弹出popupWindow后监听不到Back键
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        // 相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
        // 刷新状态（必须刷新否则无效）
        popupWindow.update();
    }

}
