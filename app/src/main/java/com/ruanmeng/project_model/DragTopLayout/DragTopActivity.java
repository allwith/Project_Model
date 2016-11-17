/*
 * Copyright 2015 chenupt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ruanmeng.project_model.DragTopLayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.google.common.collect.Lists;
import com.ruanmeng.project_model.R;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;


public class DragTopActivity extends ActionBarActivity {

    public static final String TAG = "MainActivity";

    private Toolbar toolbar;
    private DragTopLayout dragLayout;
    private ModelPagerAdapter adapter;
    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerSlidingTabStrip;

    private ImageView topImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragtop);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dragLayout = (DragTopLayout) findViewById(R.id.drag_layout);
        topImageView = (ImageView) findViewById(R.id.image_view);
        pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        toolbar.setTitle("DragTopLayout");
        setSupportActionBar(toolbar);



        // Optional setting or set them in your xml.
//        dragLayout.setOverDrag(true)
//                .setCollapseOffset(100)
//                .listener(new DragTopLayout.SimplePanelListener() {
//                    @Override
//                    public void onSliding(float ratio) {
//                        super.onSliding(ratio);
//                    }
//                })
//                .closeTopView(false);


        // init pager
        PagerModelManager factory = new PagerModelManager();
        factory.addCommonFragment(getFragments(), getTitles());
        adapter = new ModelPagerAdapter(getSupportFragmentManager(), factory);
        viewPager.setAdapter(adapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
    }

    private List<String> getTitles() {
        return Lists.newArrayList("ListView", "RecyclerView", "GridView", "ScrollView", "WebView");
    }

    private List<Fragment> getFragments() {
        List<Fragment> list = new ArrayList<>();
        Fragment listFragment = new ListViewFragment();
        Fragment recyclerFragment = new RecyclerFragment();
        Fragment gridViewFragment = new GridViewFragment();
        Fragment scrollViewFragment = new ScrollViewFragment();
        Fragment webViewFragment = new WebViewFragment();
        list.add(listFragment);
        list.add(recyclerFragment);
        list.add(gridViewFragment);
        list.add(scrollViewFragment);
        list.add(webViewFragment);
        return list;
    }


    // Handle scroll event from fragments
    public void onEvent(Boolean b) {
//        dragLayout.setTouchMode(b);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
////        int id = item.getItemId();
////
////        if (id == R.id.action_menu_icon) {
////            if (topImageView.getVisibility() == View.GONE) {
////                topImageView.setVisibility(View.VISIBLE);
////            } else {
////                topImageView.setVisibility(View.GONE);
////            }
////            return true;
////        } else if (id == R.id.action_toggle) {
////            dragLayout.toggleTopView();
////            return true;
////        } else if (id == R.id.action_over_drag) {
////            dragLayout.setOverDrag(!dragLayout.isOverDrag());
////            Toast.makeText(this, "overDrag:" + dragLayout.isOverDrag(), Toast.LENGTH_SHORT).show();
////            return true;
////        } else if (id == R.id.action_offset) {
////            if (dragLayout.getCollapseOffset() == 0) {
////                dragLayout.openTopView(true);
////                dragLayout.setCollapseOffset(200);
////            } else {
////                dragLayout.setCollapseOffset(0);
////            }
////            Toast.makeText(this, "offset:" + dragLayout.getCollapseOffset(), Toast.LENGTH_SHORT).show();
////            return true;
////        } else if (id == R.id.action_pulltorefresh) {
////            Intent intent = new Intent(this, PullToRefreshActivity.class);
////            startActivity(intent);
////            return true;
////        } else if (id == R.id.action_about) {
////            Intent intent = new Intent(this, AboutActivity.class);
////            startActivity(intent);
////            return true;
////        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
