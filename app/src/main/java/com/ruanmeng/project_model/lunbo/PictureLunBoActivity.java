package com.ruanmeng.project_model.lunbo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ruanmeng.project_model.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PictureLunBoActivity extends AppCompatActivity {

    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_lun_bo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_1, R.id.btn_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                startActivity(new Intent(PictureLunBoActivity.this,LunboActivity.class));
                break;
            case R.id.btn_2:
                startActivity(new Intent(PictureLunBoActivity.this,WuXianLunBoActivity.class));
                break;
        }
    }
}
