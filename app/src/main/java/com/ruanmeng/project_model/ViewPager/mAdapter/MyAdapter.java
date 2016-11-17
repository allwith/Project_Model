package com.ruanmeng.project_model.ViewPager.mAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */
public class MyAdapter extends FragmentPagerAdapter {


    private List<Fragment> mlist_fram = new ArrayList<>();
    private Context mContext;

    public MyAdapter(FragmentManager fm, Context mContext, List<Fragment> mlist_fram) {
        super(fm);
        this.mContext = mContext;
        this.mlist_fram = mlist_fram;
    }


    @Override
    public Fragment getItem(int position) {
        return mlist_fram.get(position);
    }

    @Override
    public int getCount() {
        return mlist_fram.size();
    }


    // TODO: 2016/11/2   暂时 设置了   mviewpager.setOffscreenPageLimit(2); 据说和 重载destroyItem 效果一样


//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
////        super.destroyItem(container, position, object);
//        container.removeView(mlist_fram.get(position).getView());
//    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return super.isViewFromObject(view, object);
    }

}
