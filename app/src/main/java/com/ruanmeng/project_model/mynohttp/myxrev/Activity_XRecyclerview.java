package com.ruanmeng.project_model.mynohttp.myxrev;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ruanmeng.nohttp.CallServer;
import com.ruanmeng.nohttp.CustomHttpListener;
import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.mynohttp.NewsM;
import com.ruanmeng.share.Const;
import com.ruanmeng.utils.CommonUtil;
import com.squareup.picasso.Picasso;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_XRecyclerview extends AppCompatActivity {


    private LinearLayout layTotleview;

    private XRecyclerView xrecycler;

    private Activity basecontext;


    private Request<String> mRequest;
    String Ip = "http://kge.gexiazi.com/tools/Interface.ashx";
    public String Img_Ip = "http://kge.gexiazi.com";

//    private BaseRecyclerAdapter<NewsM.NewsInfo> madapter = null;
    private RecyclerView.Adapter madapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xrecyclerview);
        basecontext = this;


        init();

getIndexData(true);
    }

    private void init() {

        xrecycler = (XRecyclerView) findViewById(R.id.xrecycler);
        layTotleview = (LinearLayout) findViewById(R.id.lay_totleview);

//        设置 recyclerview样式
        LinearLayoutManager layoutManager = new LinearLayoutManager(basecontext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrecycler.setLayoutManager(layoutManager);

//        设置刷新动画 和加载动画

        xrecycler.setRefreshProgressStyle(ProgressStyle.Pacman);
        xrecycler.setLoadingMoreProgressStyle(ProgressStyle.CubeTransition);
        xrecycler.setArrowImageView(R.mipmap.ic_launcher);

//         添加头布局
//        View headview = LayoutInflater.from(basecontext).inflate(R.layout.myheadview, layTotleview, false);
//        xrecycler.addHeaderView(headview);

//        设置加载数据监听
        xrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                ye = 1;
                getIndexData(true);
            }

            @Override
            public void onLoadMore() {
                ye++;
                getIndexData(false);
            }
        });
    }

    int ye = 1;
    private List<NewsM.NewsInfo> Temp_List = new ArrayList<NewsM.NewsInfo>();

    private void getIndexData(boolean isshowloading) {
        mRequest = NoHttp.createStringRequest(Ip, Const.POST);
        mRequest.add("ye", ye);
        mRequest.add("action", "news");
        mRequest.add("typeid", "1");
        mRequest.add("version", "1.0.1");

        CallServer.getRequestInstance().add(basecontext, 0, mRequest, new CustomHttpListener(basecontext, true, NewsM.class) {

            @Override
            public void doWork(Object data, boolean isOne) {

                if (ye == 1) {
                    Temp_List.clear();

                }
                Log.d("data", "data:" + data);
                Temp_List.addAll(((NewsM) data).getData());

                int newssize = ((NewsM) data).getData().size();
                int allsize = Temp_List.size();

                setRecyclerAdapter(newssize, allsize);

//           1     直接new
//                ktvAdapter = new NewsAdapter();
//                lv_ktv.setAdapter(ktvAdapter);

//          2       判断是否为空  调用notifyDataSetChanged
                /*if (ktvAdapter == null) {
                    ktvAdapter = new NewsAdapter();
                    lv_ktv.setAdapter(ktvAdapter);
                } else {
                    ktvAdapter.notifyDatasetChanged();

                }*/

            }

            @Override
            public void onFinally(JSONObject obj) {
                super.onFinally(obj);

                xrecycler.refreshComplete();
                xrecycler.loadMoreComplete();

                try {

                    if (obj == null || obj.getString("data") == null || Temp_List.size() == 0) {

                        CommonUtil.showToask(basecontext, "暂时没有新数据！");
//                        refreshLayout.setVisibility(View.GONE);
//                        layNotData.setVisibility(View.VISIBLE);
//
//                        /** 设置文字 **/
//                        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("没有更多数据");
//                        /** 隐藏图片 **/
//                        mDefineBAGRefreshWithLoadView.hideLoadingMoreImg();

                        return;
                    }


                    String str_msg = obj.getString("msg");
                    String str_msgcode = obj.getString("msgcode");

                    if (str_msgcode.equals("0")) {
//                        Toast.makeText(baseContext, str_msg, Toast.LENGTH_SHORT).show();
                        Log.d("--lfc-mc=0", str_msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, true, isshowloading);

    }

    private void setRecyclerAdapter(int newssize, int allsize) {
        if (madapter == null) {
            madapter = new BaseRecyclerAdapter<NewsM.NewsInfo>(basecontext, Temp_List) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.news_item;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, NewsM.NewsInfo item) {
//                    holder.setText(R.id.tv_title, Temp_List.get(position).getTitle());
//                    holder.setText(R.id.tv_date, Temp_List.get(position).getDate());
//                    holder.setText(R.id.tv_desc, Temp_List.get(position).getInfo());
//                    Picasso.with(mContext).load(Img_Ip + Temp_List.get(position).getLogo1()).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(holder.getImageView(R.id.img_head));

                    holder.setText(R.id.tv_title,item.getTitle());
                    holder.setText(R.id.tv_date, item.getDate());
                    holder.setText(R.id.tv_desc, item.getInfo());
                    Picasso.with(mContext).load(Img_Ip + item.getLogo1()).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(holder.getImageView(R.id.img_head));



                }


//                @Override
//                protected void bindData(RecyclerViewHolder holder, int position, Object item) {
//                    holder.setText(R.id.tv_title, Temp_List.get(position).getTitle());
//                    holder.setText(R.id.tv_date, Temp_List.get(position).getDate());
//                    holder.setText(R.id.tv_desc, Temp_List.get(position).getInfo());
//                    Picasso.with(mContext).load(Img_Ip + Temp_List.get(position).getLogo1()).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(holder.getImageView(R.id.img_head));
//
//                }
//
//                @Override
//                public void onBindViewHolder(RecyclerViewHolder holder, int position) {
//                    View view=LayoutInflater.from(basecontext).inflate(R.layout.news_item,null,false);
////                    bindData(new RecyclerViewHolder(basecontext,view), position, mItems.get(position));
//
//                }
            };
            xrecycler.setAdapter(madapter);
        } else {
            madapter.notifyDataSetChanged();
        }
    }


}
