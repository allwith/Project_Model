package com.ruanmeng.project_model.guide;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.guide.demo.CircleTextProgressbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Guide_Activity extends AppCompatActivity {

    @BindView(R.id.tv_text_skip)
    CircleTextProgressbar tvTextSkip;

    private Activity basecontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        basecontext = this;
        init();

    }

    private void init() {
        // 模拟网易新闻跳过。
//        tvTextSkip = (CircleTextProgressbar) findViewById(R.id.tv_red_skip);

//   设置 外环颜色
        tvTextSkip.setOutLineColor(Color.TRANSPARENT);
//         设置圆形的填充色
        tvTextSkip.setInCircleColor(Color.parseColor("#AAC6C6C6"));
//        设置进度条的颜色  0000FF
//        tvTextSkip.setProgressColor(Color.DKGRAY);
        tvTextSkip.setProgressColor(Color.parseColor("#aa0000FF"));

//        设置进度条线的宽度
        tvTextSkip.setProgressLineWidth(3);

//        设置时间
        tvTextSkip.setTimeMillis(5000);
        tvTextSkip.setCountdownProgressListener(100, progressListener);

        tvTextSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        开启
        tvTextSkip.reStart();
    }

    private CircleTextProgressbar.OnCountdownProgressListener progressListener = new CircleTextProgressbar.OnCountdownProgressListener() {
        @Override
        public void onProgress(int what, int progress) {
            if (what == 100) {
                switch (progress) {
                    case 0:
                        tvTextSkip.setProgressColor(Color.parseColor("#aa0000FF"));

                        basecontext.finish();
                        break;
                    case 20:
                        tvTextSkip.setProgressColor(Color.parseColor("#aaEEEE00"));

                        break;
                    case 40:
                        tvTextSkip.setProgressColor(Color.parseColor("#aa00FF00"));

                        break;
                    case 60:
                        tvTextSkip.setProgressColor(Color.parseColor("#aaB3EE3A"));

                        break;
                    case 80:
                        tvTextSkip.setProgressColor(Color.parseColor("#aaFF0000"));

                        break;
                    case 100:
                        tvTextSkip.setProgressColor(Color.parseColor("#aa0000FF"));

                        break;
                }
            }
        }
    };
}
