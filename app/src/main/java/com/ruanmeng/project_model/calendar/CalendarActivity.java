package com.ruanmeng.project_model.calendar;

import android.os.Bundle;
import android.view.View;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.project_model.R;

public class CalendarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        init_title();
        changeTitle("日历", null);
    }

    @Override
    public void doClick(View v) {
        super.doClick(v);
        switch (v.getId()) {
            case R.id.button8:
                startActivity(Calendar1Activity.class);
                break;
            case R.id.button9:
                startActivity(Calendar2Activity.class);
                break;
            case R.id.button10:
                startActivity(Calendar3Activity.class);
                break;
        }
    }

}
