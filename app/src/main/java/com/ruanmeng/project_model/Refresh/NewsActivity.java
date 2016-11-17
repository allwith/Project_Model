package com.ruanmeng.project_model.Refresh;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.animator.ItemAnimatorFactory;
import com.ruanmeng.nohttp.CallServer;
import com.ruanmeng.project_model.R;
import com.ruanmeng.share.Const;
import com.ruanmeng.share.HttpIP;
import com.ruanmeng.utils.CustomRecyclerView;
import com.yolanda.nohttp.NoHttp;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends BaseActivity {


    @BindView(R.id.lv_news_list)
    CustomRecyclerView mRecyclerView;
    @BindView(R.id.rl_news_refresh)
    SwipeRefreshLayout mRefresh;
    private LinearLayoutManager linearLayoutManager;
    private List<NewsModel.NewsInfo> list = new ArrayList<>();
    private NewsAdapter adapter;
    private int index = 1;
    private boolean isLoadingMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        init_title();
        changeTitle("平台公告", null);
        getData(index, true);
    }

    @Override
    public void init_title() {
        super.init_title();
        mRefresh.setColorSchemeResources(R.color.colorAccent);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        mRecyclerView.setItemAnimator(ItemAnimatorFactory.slidein());

        adapter = new NewsAdapter(this, R.layout.item_news_list, list);
        mRecyclerView.setAdapter(adapter);

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (list.size() > 0) {
                    list.clear();
                    adapter.notifyDataSetChanged();
                }

                index = 1;
                getData(index, false);
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
                        getData(index, false);
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
    }

    private void getData(int pindex, boolean isLoading) {
        mRequest = NoHttp.createStringRequest(HttpIP.news_ptgg, Const.POST);
        mRequest.add("pagenum", pindex);
        mRequest.add("pagesize", 10);
        // 添加到请求队列
        CallServer.getRequestInstance().add(this, 0, mRequest,
                new CustomHttpListener2(baseContext, true, NewsModel.class) {
                    @Override
                    public void doWork(Object data, boolean isOne) {
                        if (index == 1)
                            list.clear();

                        // 启动动画的关键位, 顺次添加动画效果
                        int pos = adapter.getItemCount();
                        list.addAll(((NewsModel) data).getData().getList());

                        Log.d("--lfc", "集合长度为：" + list.size());
                        adapter.notifyItemRangeInserted(pos, list.size());

                        index++;
                    }

                    @Override
                    public void onFinally(JSONObject obj) {
                        super.onFinally(obj);
                        mRefresh.setRefreshing(false);
                        isLoadingMore = false;
                    }
                }, true, isLoading);
    }

}
