package com.ruanmeng.project_model.shopping;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.project_model.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopActivity extends BaseActivity {

    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        init_title();
        changeTitle("购物车",null);
    }

    @OnClick({R.id.button4, R.id.button5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button4:
                startActivity(ChuiZhiShoppingActivity.class);
                break;
            case R.id.button5:
                startActivity(RuZhuShoppingActivity.class);
                break;
        }
    }
}
