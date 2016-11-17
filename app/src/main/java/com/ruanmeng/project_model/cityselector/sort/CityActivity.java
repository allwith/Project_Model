package com.ruanmeng.project_model.cityselector.sort;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.cityselector.CityModel;
import com.ruanmeng.project_model.cityselector.QuActivity;
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

public class CityActivity extends BaseActivity {

    @BindView(R.id.lv_city_list)
    RecyclerView mRecyclerView;

    private AreaAdapter adapter;
    private List<CityModel.CityInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
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
        mRequest = NoHttp.createStringRequest("http://api.zzwms.com/public/selectcity", Const.POST);
        mRequest.add("province_id", getIntent().getStringExtra("id_province"));
        // 添加到请求队列
        CallServer.getRequestInstance().add(this, 0, mRequest,
                new CustomHttpListener(baseContext, true, CityModel.class) {
                    @Override
                    public void doWork(Object data, String code) {

                        // 启动动画的关键位, 顺次添加动画效果
                        int pos = adapter.getItemCount();
                        list.addAll(((CityModel) data).getData().getCity());
                        adapter.notifyItemRangeInserted(pos, list.size());

                    }
                }, true, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200 && data != null) {
            setResult(200, data);
            onBackPressed();
        }
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
                    data.setClass(mContext, QuActivity.class);
                    data.putExtra("id_city", info.getNid());
                    data.putExtra("title_city", info.getTitle());
                    startActivityForResult(data, 100);
                }
            });
        }
    }

}
