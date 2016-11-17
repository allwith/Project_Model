package com.ruanmeng.project_model.cityselector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.project_model.R;
import com.ruanmeng.share.Const;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.demo.CallServer;
import com.yolanda.nohttp.demo.CustomHttpListener;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuActivity extends BaseActivity {

    @BindView(R.id.lv_qu_list)
    RecyclerView mRecyclerView;

    private AreaAdapter adapter;
    private List<CityModel.CityInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qu);
        ButterKnife.bind(this);
        init_title();
        changeTitle("选择城市", null);

        getData();
    }

    @Override
    public void init_title() {
        super.init_title();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new AreaAdapter(this, R.layout.item_area_list, list);
        mRecyclerView.setAdapter(adapter);

    }

    private void getData() {
        int pos = adapter.getItemCount();
        list.add(new CityModel.CityInfo("1", "二七区"));
        list.add(new CityModel.CityInfo("2", "金水区"));
        list.add(new CityModel.CityInfo("3", "管城区"));
        list.add(new CityModel.CityInfo("4", "惠济区"));
        adapter.notifyItemRangeInserted(pos, list.size());
    }

    private class AreaAdapter extends CommonAdapter<CityModel.CityInfo> {

        public AreaAdapter(Context context, int layoutId, List<CityModel.CityInfo> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, final CityModel.CityInfo info) {
            holder.setText(R.id.tv_brand_item_title, info.getTitle());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = getIntent();
                    data.putExtra("id_qu", info.getId());
                    data.putExtra("title_qu", info.getTitle());
                    setResult(200, data);
                    onBackPressed();
                }
            });
        }
    }

}
