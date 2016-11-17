package com.ruanmeng.project_model.ViewPager.myui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.ViewPager.ext.titles.ScaleTransitionPagerTitleView;
import com.ruanmeng.project_model.ViewPager.mAdapter.MyAdapter;
import com.ruanmeng.project_model.ViewPager.mFragment.Fragment_01;
import com.ruanmeng.project_model.ViewPager.mFragment.Fragment_02;
import com.ruanmeng.project_model.ViewPager.mFragment.Fragment_03;
import com.ruanmeng.project_model.ViewPager.mFragment.Fragment_04;
import com.ruanmeng.project_model.ViewPager.mFragment.Fragment_05;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要用到 这个 依赖包magicindicator
 */
public class MyViewpager extends AppCompatActivity {


    private MagicIndicator magicIndicator2;
    private ViewPager mviewpager;

    private List<String> mlist_title = new ArrayList<>();
    private List<Fragment> mlist_fragment = new ArrayList<>();


    //     viewpager 适配器
    private MyAdapter myAdapte;


    private Fragment_01 fm_01;
    private Fragment_02 fm_02;
    private Fragment_03 fm_03;
    private Fragment_04 fm_04;
    private Fragment_05 fm_05;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_viewpager);

        initData();
        initView();
        initAdapter();


    }

    private void initData() {

        String string = "";
        for (int i = 0; i < 5; i++) {

            string = "第" + i + "个";
            mlist_title.add(string);
        }
//        mExamplePagerAdapter = new ExamplePagerAdapter(mlist_title);
    }

    private void initAdapter() {
        fm_01 = new Fragment_01();
        fm_02 = Fragment_02.getInstance();
        fm_03 = Fragment_03.getInstance();
        fm_04 = Fragment_04.getInstance();
        fm_05 = Fragment_05.getInstance();

        mlist_fragment.add(fm_01);
        mlist_fragment.add(fm_02);
        mlist_fragment.add(fm_03);
        mlist_fragment.add(fm_04);
        mlist_fragment.add(fm_05);

        FragmentManager fmg = getSupportFragmentManager();

        myAdapte = new MyAdapter(fmg, MyViewpager.this, mlist_fragment);
        mviewpager.setOffscreenPageLimit(2);
        mviewpager.setAdapter(myAdapte);
    }

    private void initView() {
        magicIndicator2 = (MagicIndicator) findViewById(R.id.magic_indicator2);
        mviewpager = (ViewPager) findViewById(R.id.mviewpager);
//        mviewpager.setAdapter(mExamplePagerAdapter);
//        magicIndicator2.setBackgroundColor(Color.parseColor("#00c853"));
        magicIndicator2.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
//        commonNavigator.setScrollPivotX(0.5f);
//         设置不可移动 固定屏幕宽度
        commonNavigator.setAdjustMode(true);

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                if (mlist_title != null) {
                    return mlist_title.size();
                } else {
                    return 0;
                }
//                return 0;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
//                  设置为 可以转化动画效果的  textview
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mlist_title.get(index));
/*

//                 设置正常时候的  字体颜色
                simplePagerTitleView.setNormalColor(R.color.white);
//                 设置选择时候的 字体颜色
                simplePagerTitleView.setSelectedColor(R.color.colorAccent);
                simplePagerTitleView.setTextSize(18);
*/

                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.parseColor("#616161"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#f57c00"));

//                点击事件
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mviewpager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                //                 设置指示器动画
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(1.5f));
//                  indicator.setYOffset(UIUtil.dip2px(context, 39));  //  设置指示器 的位置在上方
//                 设置底部指示器的高度
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
//                 设置指示器的颜色
//                indicator.setColors(R.color.my_test);
                indicator.setColors(Color.parseColor("#f57c00"), Color.parseColor("#f57cff"), Color.parseColor("#f55555"), Color.parseColor("#85ac99"));
                indicator.setColors(Color.parseColor("#f57c00"));

                return indicator;
            }


//            @Override
//            public float getTitleWeight(Context context, int index) {
//                if (index == 0) {
//                    return 2.0f;
//                } else if (index == 1) {
//                    return 1.2f;
//                } else {
//                    return 1.0f;
//                }
//            }

        });

        magicIndicator2.setNavigator(commonNavigator);


//        设置 间隔线
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(this, 15));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter2));
        ViewPagerHelper.bind(magicIndicator2, mviewpager);

    }
}
