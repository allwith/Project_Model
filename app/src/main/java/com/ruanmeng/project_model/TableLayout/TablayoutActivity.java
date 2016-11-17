package com.ruanmeng.project_model.TableLayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.ruanmeng.project_model.R;


/**
 * Created by Administrator on 2016/8/3.
 */
public class TablayoutActivity extends AppCompatActivity {

    /**
     * ----------------------------------- 固定的page--->TabLayout
     *
     * @param savedInstanceState
     */
    private TabLayout tabLayout = null;

    private ViewPager vp_pager;

    private Fragment[] mFragmentArrays = new Fragment[3];

    private String[] mTabTitles = new String[3];

    private int type;

    private int[] img = {android.R.drawable.btn_star_big_on,
            android.support.v7.appcompat.R.drawable.abc_btn_radio_material,
            android.support.v7.appcompat.R.drawable.abc_scrubber_control_to_pressed_mtrl_000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tab_layout);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        vp_pager = (ViewPager) findViewById(R.id.tab_viewpager);

        type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case 0:
                fixedPager();
                break;
            case 1:
                morePager();
                break;
            case 2:
                graphicPager();
                break;
        }



    }

    private void fixedPager() {
        mTabTitles[0] = "朋友";
        mTabTitles[1] = "我的";
        mTabTitles[2] = "动态";


        //设置TabLayout的模式,这里主要是用来显示tab展示的情况的
        //TabLayout.MODE_FIXED          各tab平分整个工具栏,如果不设置，则默认就是这个值
        //TabLayout.MODE_SCROLLABLE     适用于多tab的，也就是有滚动条的，一行显示不下这些tab可以用这个


        // -------------------------------事列1(固定的)

        tabLayout.setTabMode(TabLayout.MODE_FIXED);//固定的




        mFragmentArrays[0] = AuthorInfoFragment.newInstance();
        mFragmentArrays[1] = AuthorInfoFragment.newInstance();
        mFragmentArrays[2] = AuthorInfoFragment.newInstance();

        PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        vp_pager.setAdapter(pagerAdapter);
        //增加Tab，同时关联ViewPager
        tabLayout.setupWithViewPager(vp_pager);


    }

    private void morePager() {

        // -------------------------------多条tab数据

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        vp_pager.setAdapter(new MorePagerAdapter());

        // TabLayout和ViewPager双向、交互联动。
        tabLayout.setupWithViewPager(vp_pager);

    }
    // -------------------------------图文tab
    private void graphicPager() {
        fixedPager();
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setIcon(img[i]);

        }
    }

    final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }


        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];//固定

        }
    }

    final class MorePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tv = new TextView(TablayoutActivity.this);
            tv.setText("ViewPager" + position);
            tv.setTextSize(30.0f);
            tv.setGravity(Gravity.CENTER);
            ((ViewGroup) container).addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "选项" + position;
        }
    }

    /**
     * TabLayout的属性：
     *
     addTab(TabLayout.Tab tab, int position, boolean setSelected) 增加选项卡到 layout 中
     addTab(TabLayout.Tab tab, boolean setSelected) 同上
     addTab(TabLayout.Tab tab) 同上
     getTabAt(int index) 得到index选项卡
     getTabCount() 得到选项卡的总个数
     getTabGravity() 得到 tab 的 Gravity
     getTabMode() 得到 tab 的模式
     getTabTextColors() 得到 tab 中文本的颜色
     newTab() 新建个 tab
     removeAllTabs() 移除所有的 tab
     removeTab(TabLayout.Tab tab) 移除指定的 tab
     removeTabAt(int position) 移除指定位置的 tab
     setOnTabSelectedListener(TabLayout.OnTabSelectedListener onTabSelectedListener) 为每个 tab 增加选择监听器，稍候会讲解其回调方法
     setScrollPosition(int position, float positionOffset, boolean updateSelectedText) 设置滚动位置
     setTabGravity(int gravity) 设置 Gravity（fill填充，center居中）
     setTabMode(int mode) 设置 Mode（设置可否滑动，scrollable可滑动）
     setTabTextColors(ColorStateList textColor) 设置 tab 中文本的颜色
     setTabTextColors(int normalColor, int selectedColor) 设置 tab 中文本的颜色 默认 选中
     setTabsFromPagerAdapter(PagerAdapter adapter) 设置 PagerAdapter
     setupWithViewPager(ViewPager viewPager) 和 ViewPager 联动
     */
}
