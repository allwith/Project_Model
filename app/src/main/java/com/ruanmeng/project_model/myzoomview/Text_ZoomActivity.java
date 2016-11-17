package com.ruanmeng.project_model.myzoomview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.ruanmeng.project_model.R;
import com.ruanmeng.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Text_ZoomActivity extends AppCompatActivity {

    @BindView(R.id.scroll_view)
    PullToZoomScrollViewEx scrollView;

    private Activity basecontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_zoom);

        basecontext = this;
        // 给左上角图标的左边加上一个返回的图标 。
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisPlayHomeAsUpEnabled(true);
        ButterKnife.bind(this);


        initZoomViewe();
    }

    private void initZoomViewe() {


        View headview = LayoutInflater.from(basecontext).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(basecontext).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(basecontext).inflate(R.layout.profile_content_view, null, false);
        scrollView.setHeaderView(headview);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);

        scrollView.getPullRootView().findViewById(R.id.tv_test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showToask(basecontext,"条目一默认时间");
            }
        });

        scrollView.getPullRootView().findViewById(R.id.tv_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showToask(basecontext,"条目2--3秒",5000);
            }
        });

    }
}
