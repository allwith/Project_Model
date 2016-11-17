package com.ruanmeng.project_model.Refresh;

import android.content.Context;
import android.view.View;

import com.ruanmeng.project_model.R;
import com.ruanmeng.utils.TimeHelper;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

public class NewsAdapter extends CommonAdapter<NewsModel.NewsInfo> {

    public NewsAdapter(Context context, int layoutId, List<NewsModel.NewsInfo> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, NewsModel.NewsInfo info) {
        holder.setText(R.id.tv_item_news_title, info.getName());
        holder.setText(R.id.tv_item_news_time,
                TimeHelper.getInstance().getFormatedDateTime("yyyy-MM-dd", Long.parseLong(info.getAddtime())));
        holder.setText(R.id.tv_item_news_num, info.getHits());

        View foot1 = holder.getView(R.id.v_item_news_divider_1);
        View foot2 = holder.getView(R.id.v_item_news_divider_2);
        if (holder.getLayoutPosition() + 1 == mDatas.size()) {
            foot1.setVisibility(View.GONE);
            foot2.setVisibility(View.VISIBLE);
        }
        else {
            foot1.setVisibility(View.VISIBLE);
            foot2.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });
    }
}
