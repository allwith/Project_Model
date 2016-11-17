package com.ruanmeng.project_model.orderlist;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.project_model.R;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.rg_fragment_order_check)
    RadioGroup rg;
    @BindView(R.id.lv_fragment_order_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.rl_fragment_order_refresh)
    SwipeRefreshLayout mRefresh;

    private ArrayList<String> list = new ArrayList<>();
    private OrderAdapter adapter;
    private int index = 1;
    private LinearLayoutManager linearLayoutManager;
    private boolean isLoadingMore;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getData();
            mRefresh.setRefreshing(false);
            isLoadingMore = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        init_title();
        changeTitle("订单列表", null);

        rg.getChildAt(0).performClick();
    }

    private void getData() {
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        adapter.notifyDataSetChanged();
    }

    @Override
    public void init_title() {
        super.init_title();
        mRefresh.setColorSchemeResources(R.color.colorPrimaryDark);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new OrderAdapter(this, R.layout.item_order_list, list);
        mRecyclerView.setAdapter(adapter);

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                index = 1;
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int total = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy > 0 表示向下滑动
                if (lastVisibleItem >= total - 1 && dy > 0) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        handler.sendEmptyMessage(0);
                    }
                }
            }
        });

        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mRefresh.isRefreshing()) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                index = 1;
                list.clear();
                adapter.notifyDataSetChanged();
                switch (checkedId) {
                    case R.id.rb_fragment_order_check_1:
                        break;
                    case R.id.rb_fragment_order_check_2:
                        break;
                    case R.id.rb_fragment_order_check_3:
                        break;
                    case R.id.rb_fragment_order_check_4:
                        break;
                }
                getData();
            }
        });
    }

    private class OrderAdapter extends CommonAdapter<String> {

        public OrderAdapter(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, String s) {

        }
    }
}
