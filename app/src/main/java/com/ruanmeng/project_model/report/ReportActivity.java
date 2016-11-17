package com.ruanmeng.project_model.report;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.project_model.R;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportActivity extends BaseActivity {

    @BindView(R.id.lv_report_list)
    RecyclerView mRecyclerView;

    private ReportAdapter adapter;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        init_title();
        changeTitle("新闻列表", null);

        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ReportAdapter(this, R.layout.item_report_list, list);
        mRecyclerView.setAdapter(adapter);
    }

    private class ReportAdapter extends CommonAdapter<String > {

        public ReportAdapter(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, String s) {
            View foot1 = holder.getView(R.id.v_item_report_divider_1);
            View foot2 = holder.getView(R.id.v_item_report_divider_2);
            if (holder.getLayoutPosition() + 1 == mDatas.size()) {
                foot1.setVisibility(View.GONE);
                foot2.setVisibility(View.VISIBLE);
            }
            else {
                foot1.setVisibility(View.VISIBLE);
                foot2.setVisibility(View.GONE);
            }
        }
    }
}
