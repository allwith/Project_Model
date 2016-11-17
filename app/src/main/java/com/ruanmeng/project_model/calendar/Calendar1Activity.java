package com.ruanmeng.project_model.calendar;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.calendarview.CalendarCard;
import com.ruanmeng.calendarview.CalendarViewAdapter;
import com.ruanmeng.calendarview.CustomDate;
import com.ruanmeng.project_model.R;
import com.ruanmeng.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Calendar1Activity extends BaseActivity {

    @BindView(R.id.tv_trans_time_1)
    TextView tv_time;
    @BindView(R.id.vp_payment_check_calendar)
    ViewPager mViewPager;

    private int month = 7;

    private CalendarViewAdapter<CalendarCard> adapter;
    private SildeDirection mDirection = SildeDirection.NO_SILDE;
    enum SildeDirection { RIGHT, LEFT, NO_SILDE;}
    private int mCurrentIndex = 498;
    private CalendarCard[] mShowViews;
    private List<String> dates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar1);
        ButterKnife.bind(this);
        init_title();
        changeTitle("日历", null);

        getData();
    }

    @Override
    public void init_title() {
        super.init_title();
        CalendarCard[] views = new CalendarCard[3];
        for (int i = 0; i < 3; i++) {
            views[i] = new CalendarCard(this, new CalendarCard.OnCellClickListener() {
                @Override
                public void clickDate(CustomDate date) {
                    CommonUtil.showToask(baseContext, date.year + "年"+ date.month + "月"+ date.day + "日");
                }

                @Override
                public void changeDate(CustomDate date) {

                    tv_time.setText(date.year + "年"+ date.month + "月");
                    month = date.month;
                    if (date.month < 9) {
                        tv_time.setText(date.year + "年0" + date.month + "月");
                    }

                }
            });
        }
        adapter = new CalendarViewAdapter<>(views);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(mCurrentIndex);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                measureDirection(position);
                updateCalendarView(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) { }

            @Override
            public void onPageScrollStateChanged(int arg0) { }
        });
    }

    private void getData() {
        dates.clear();
        dates.add("2016-" + month + "-5");
        dates.add("2016-" + month + "-10");
        dates.add("2016-" + month + "-16");
        dates.add("2016-" + month + "-25");
        int page = mViewPager.getCurrentItem() % 3;
        mShowViews = adapter.getAllItems();
        mShowViews[page].setQiandaoList(dates);
    }

    /**
     * 计算方向
     */
    private void measureDirection(int pos) {

        if (pos > mCurrentIndex) {
            mDirection = SildeDirection.RIGHT;

        } else if (pos < mCurrentIndex) {
            mDirection = SildeDirection.LEFT;
        }
        mCurrentIndex = pos;
    }

    // 更新日历视图
    private void updateCalendarView(int arg0) {
        mShowViews = adapter.getAllItems();
        if (mDirection == SildeDirection.RIGHT) {
            mShowViews[arg0 % mShowViews.length].rightSlide();
        } else if (mDirection == SildeDirection.LEFT) {
            mShowViews[arg0 % mShowViews.length].leftSlide();
        }
        mDirection = SildeDirection.NO_SILDE;
        getData();
    }
}
