package com.ruanmeng.project_model.ScrollViewHeader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ruanmeng.project_model.R;

/**
 * 只不过重写ScrollView是ScrollView向ios具有弹性，悬浮头只不过是添加两个Header，一个正在正常显示，那一个具体监听ScrollView，
 * 给用户以假乱真的样子。具体头部在Google Design库可以做到。以下代码是学习使用，就这么简单！！！
 */
public class ScrollviewHeaderActivity extends AppCompatActivity implements SpringScrollView.OnScrollListener {
    private TextView invis;
    private SpringScrollView sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_scrolviewheader);
        invis = (TextView) findViewById(R.id.invis);
        sc = (SpringScrollView) findViewById(R.id.myScroll);
        sc.setOnScrollListener(this);
    }

    /**
     * @param deltaY 监听手势的方向
     */
    @Override
    public void onScroll(int deltaY) {
        if (deltaY < 0) {
            //下拉 不显示头部
            invis.setVisibility(View.GONE);
        } else {

            // 上拉 显示头部
            invis.setVisibility(View.VISIBLE);
        }
    }
}
