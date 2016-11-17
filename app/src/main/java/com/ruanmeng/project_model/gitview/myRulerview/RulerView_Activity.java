package com.ruanmeng.project_model.gitview.myRulerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.ruanmeng.project_model.R;
import com.xk.sanjay.rulberview.RulerWheel;

import java.util.ArrayList;
import java.util.List;

public class RulerView_Activity extends AppCompatActivity {

    private TextView curValueTv;
    private RulerWheel rulerView;
    private TextView curValue2Tv;
    private RulerWheel rulerView2;


    private List<String> list = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruler_view);


        initData();
        initView();
    }

    private void initData() {

        for (int i = 85; i < 111; i += 1) {
            list.add(i + "");
            for (int j = 1; j < 10; j++) {
                list.add(i + "." + j);
            }
        }
        // TODO: 2016/11/15  添加最后一个  否则 最后一个数据不显示
        list.add(111 + "");


        for (int i = 1000; i < 50000; i += 500) {
            list2.add(i + "");
        }

    }

    private void initView() {
        curValueTv = (TextView) findViewById(R.id.curValue_tv);
        rulerView = (RulerWheel) findViewById(R.id.ruler_view);

        rulerView.setData(list);
        rulerView.setSelectedValue(list.get(list.size() / 2));
//        rulerView.setDataModel(rulerw);
        rulerView.setScrollingListener(new RulerWheel.OnWheelScrollListener<String>() {



            @Override
//                                                       old value    new  value
            public void onChanged(RulerWheel rulerWheel, String s, String t1) {
                curValueTv.setText(t1 + "");
            }

            @Override
            public void onScrollingStarted(RulerWheel rulerWheel) {

            }

            @Override
            public void onScrollingFinished(RulerWheel rulerWheel) {

//                获取当前的 posiition
                String s = rulerWheel.getValue() + "";
//            获取 当前的 数据值

                String string = list.get(rulerWheel.getValue());

                Log.d("---lfc", "s: " + s + "\n   string: " + string);
            }
        });

        curValue2Tv = (TextView) findViewById(R.id.curValue2_tv);
        rulerView2 = (RulerWheel) findViewById(R.id.ruler_view2);

        rulerView2.setData(list2);
        rulerView2.setDataModel(RulerWheel.DATA_SET);
        rulerView2.setSelectedValue("8000");
        rulerView2.setScrollingListener(new RulerWheel.OnWheelScrollListener<String>() {
            @Override
            public void onChanged(RulerWheel wheel, String oldValue, String newValue) {
                curValue2Tv.setText(newValue + "");
            }

            @Override
            public void onScrollingStarted(RulerWheel wheel) {

            }

            @Override
            public void onScrollingFinished(RulerWheel wheel) {

            }
        });

    }
}
