package com.ruanmeng.project_model.lunbo;

import android.os.Bundle;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.ruanmeng.BaseActivity;
import com.ruanmeng.nohttp.CallServer;
import com.ruanmeng.project_model.R;
import com.ruanmeng.share.Const;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WuXianLunBoActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener {

    @BindView(R.id.liamneng_slider)
    SliderLayout mDemoSlider;
    @BindView(R.id.custom_indicator2)
    PagerIndicator customIndicator2;
    private PPLunBoM lunBoM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wu_xian_lun_bo);
        ButterKnife.bind(this);
        changeTitle("无限轮播", null);
        init_title();
        getlunbo();
    }

    private void lunbotu() {
//        mDemoSlider.setFocusable(false);
//------------------------------------------------------------------
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
        arrayList.clear();
        mDemoSlider.removeAllSliders();

//        int len = img.length;
        for (int i = 0; i < lunBoM.getData().size(); i++) {
            HashMap<String, Object> allHashMap = new HashMap<String, Object>();
            allHashMap.put("name", "" + i);
            allHashMap.put("content", "=" + i);
            allHashMap.put("code","http://122.114.98.248:1170" + lunBoM.getData().get(i).getImage());
            arrayList.add(allHashMap);
        }

        for (int i = 0; i < lunBoM.getData().size(); i++) {
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            textSliderView
                    .description(arrayList.get(i).get("name").toString())
                    //.image(arrayList.get(i).get("code").toString())
                    .image(arrayList.get(i).get("code").toString())
//                    .image(img[i])

                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("name", arrayList.get(i).get("name").toString());
            textSliderView.getBundle().putString("content", arrayList.get(i).get("content").toString());
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);//设置默认过渡效果(可在xml中设置)的动画
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom); // 设置自定义指示器(位置自定义)
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());//效果
        mDemoSlider.setDuration(3000);//设置时间 这个时间 就是延迟时间 第一个图跟第2个图3秒间隔
    }
    /**
     * 获取轮播图
     */
    public void getlunbo() {
        Request<String> mRequest = NoHttp.createStringRequest("http://122.114.98.248:1170/interfaceDOC/firstProject/Outskirts.ashx?", Const.POST);
        mRequest.add("action", "Get_AdsitImage");
        mRequest.add("flag", "0");
//         添加到请求队列
        CallServer.getRequestInstance().add(this, 0, mRequest,
                new CustomHttpListener5(this, true, PPLunBoM.class) {
                    @Override
                    public void doWork(Object data, boolean isOne) {
                        try {
                            lunBoM = (PPLunBoM) data;
                            lunbotu();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, true, true);
    }
    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
