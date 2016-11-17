package com.ruanmeng.project_model.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ruanmeng.project_model.GaoDeMap.Location_Activity;
import com.ruanmeng.project_model.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends AppCompatActivity {

    @BindView(R.id.btn_baidu)
    Button btnBaidu;
    @BindView(R.id.btn_gaode)
    Button btnGaode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_baidu, R.id.btn_gaode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_baidu:
                startActivity(new Intent(MapActivity.this,LocationActivity.class));
                break;
            case R.id.btn_gaode:
                startActivity(new Intent(MapActivity.this,Location_Activity.class));
                break;
        }
    }
}
