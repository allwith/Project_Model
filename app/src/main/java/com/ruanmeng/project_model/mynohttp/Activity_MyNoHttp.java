package com.ruanmeng.project_model.mynohttp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.ruanmeng.BaseActivity;
import com.ruanmeng.nohttp.CallServer;
import com.ruanmeng.nohttp.CustomHttpListener;
import com.ruanmeng.project_model.R;
import com.ruanmeng.share.Const;
import com.ruanmeng.utils.CommonUtil;
import com.squareup.picasso.Picasso;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_MyNoHttp extends BaseActivity {


    private com.handmark.pulltorefresh.library.PullToRefreshScrollView refreshLayout;
    private MyListView lv_ktv;
    private Button btn_title;
    private Activity baseContext;
    private RelativeLayout lay_no_data;




    private Request<String> mRequest;
    String Ip = "http://kge.gexiazi.com/tools/Interface.ashx";
    public String Img_Ip = "http://kge.gexiazi.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_no_http);

        baseContext = this;
        init_title();
        changeTitle("娱乐资讯", "歌匣子");
        init();
        getIndexData();


    }

    private void getIndexData() {

        mRequest = NoHttp.createStringRequest(Ip, Const.POST);
        mRequest.add("ye", ye);
        mRequest.add("action", "news");
        mRequest.add("typeid", "1");
        mRequest.add("version", "1.0.1");

//        params.put("version", Params.APP_Version);


 /*       CallServer.getRequestInstance().add(Activity_MyNoHttp.this,0, new CustomHttpListener(Activity_MyNoHttp.this,true,NewsM.class)
        {

            @Override
            public void doWork(Object data, boolean isOne) {

            }
        },true,true);*/
//        NewsM.class

        CallServer.getRequestInstance().add(baseContext, 0, mRequest, new CustomHttpListener(baseContext, true, NewsM.class) {

//            @Override
//            public void doWork(Object data, String msgcode) {
//                Log.d("data", "data:" + data);
//                Temp_List.addAll(((NewsM) data).getData());
//
//                if (ktvAdapter == null) {
//                    ktvAdapter = new NewsAdapter();
//                    lv_ktv.setAdapter(ktvAdapter);
//                } else {
//                    ktvAdapter.notifyDataSetChanged();
//                }
//
//
//            }

            @Override
            public void doWork(Object data, boolean isOne) {


                if (ye == 1) {
                    Temp_List.clear();

                }
                Log.d("data", "data:" + data);
                Temp_List.addAll(((NewsM) data).getData());

//           1     直接new
                ktvAdapter = new NewsAdapter();
                lv_ktv.setAdapter(ktvAdapter);

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

                refreshLayout.onRefreshComplete();

//                int count = ktvAdapter.getCount();
//
//
//                if (count<=0){
//                    refreshLayout.setVisibility(View.GONE);
//                    lay_no_data.setVisibility(View.VISIBLE);
//                }

                try {

                    if (obj == null || obj.getString("data") == null || Temp_List.size() == 0) {

//                        CommonUtil.showToask(baseContext, "暂时没有新数据！");

                        refreshLayout.setVisibility(View.GONE);
                        lay_no_data.setVisibility(View.VISIBLE);
//

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
        }, true, true);

    }

    private int ye = 1;
    private List<NewsM.NewsInfo> Temp_List = new ArrayList<NewsM.NewsInfo>();

    private NewsAdapter ktvAdapter;


    private void init() {

        lay_no_data = (RelativeLayout) findViewById(R.id.lay_no_data);
        refreshLayout = (com.handmark.pulltorefresh.library.PullToRefreshScrollView) findViewById(R.id.refreshLayout);
        refreshLayout.setMode(PullToRefreshBase.Mode.BOTH);
        refreshLayout.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                ye = 1;
//                Temp_List.clear();
//                indexData = null;
                getIndexData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                ye++;
                getIndexData();

            }
        });


        //猜你喜欢
        lv_ktv = (MyListView) findViewById(R.id.lv_zixun);
    }

    private class NewsAdapter extends BaseSwipeAdapter {

        public NewsAdapter() {
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return Temp_List.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return Temp_List.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }


        @Override
        public int getSwipeLayoutResourceId(int position) {
            return R.id.mytext_swip;
        }

        @Override
        public void notifyDatasetChanged() {
            ktvAdapter.notifyDataSetChanged();
        }

        @Override
        public View generateView(final int position, ViewGroup parent) {

            View view = null;
            if (view == null) {
//                view = LayoutInflater.from(Activity_MyNoHttp.this).inflate(R.layout.news_item, null);
                view = LayoutInflater.from(Activity_MyNoHttp.this).inflate(R.layout.news_item_copy, null);
            }

            final ImageView img_head = (ImageView) view.findViewById(R.id.img_head);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
            TextView tv_desc = (TextView) view.findViewById(R.id.tv_desc);

            LinearLayout lay_item = (LinearLayout) view.findViewById(R.id.lay_item_nohttp);

            SwipeLayout mytext_swip = (SwipeLayout) view.findViewById(R.id.mytext_swip);


            mytext_swip.addSwipeListener(new SimpleSwipeListener() {
                @Override
                public void onOpen(SwipeLayout layout) {
                    super.onOpen(layout);
//                     颤抖动画效果
                    YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.item_sliding_love));

                }

                @Override
                public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                    super.onUpdate(layout, leftOffset, topOffset);

                }
            });
            mytext_swip.setShowMode(SwipeLayout.ShowMode.PullOut);
            mytext_swip.addDrag(SwipeLayout.DragEdge.Right, mytext_swip.findViewById(R.id.lay_ringht_view));

            mytext_swip.findViewById(R.id.item_sliding_love).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    CommonUtil.showToask(baseContext, "收藏：" + Temp_List.get(position).getTitle());
                }
            });
            mytext_swip.findViewById(R.id.item_sliding_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    CommonUtil.showToask(baseContext,"删除："+Temp_List.get(position).getTitle());

                    Log.d("--lfc \n", "删除前" + position + "  Temp_List.size：" + Temp_List.size());

                    Temp_List.remove(position);

                    Log.d("--lfc \n", "删除后" + position + "  Temp_List.size：" + Temp_List.size());

//                    List mlist = new ArrayList();
//                    mlist=Temp_List;
//                    Temp_List.clear();
//
//                    Temp_List.addAll(mlist);
//                    if (ktvAdapter == null) {
//                        ktvAdapter = new NewsAdapter();
//                        lv_ktv.setAdapter(ktvAdapter);
//                    } else {
//                        ktvAdapter.notifyDatasetChanged();
//
//                    }


//                    这个操作 太假了 不正规 正常的应该是 接口删除数据  然后重新获取数据 本地数据暂时先这样操作

                    ktvAdapter = new NewsAdapter();
                    lv_ktv.setAdapter(ktvAdapter);
                    ktvAdapter = null;
                }
            });
            mytext_swip.getSurfaceView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CommonUtil.showToask(baseContext, "--进入详情--：" + Temp_List.get(position).getTitle());

                }
            });

//            lay_item.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    CommonUtil.showToask(baseContext, "进入详情" + Temp_List.get(position).getTitle());
//                }
//            });

            Picasso.with(Activity_MyNoHttp.this).load(Img_Ip + Temp_List.get(position).getLogo1()).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(img_head);
            tv_title.setText(Temp_List.get(position).getTitle());
            tv_date.setText(Temp_List.get(position).getDate());
            tv_desc.setText(Temp_List.get(position).getInfo());
            return view;
        }

        @Override
        public void fillValues(int position, View convertView) {

        }
/*
        @Override
        public View getView(final int position, View view, ViewGroup arg2) {
            if (view == null) {
//                view = LayoutInflater.from(Activity_MyNoHttp.this).inflate(R.layout.news_item, null);
                view = LayoutInflater.from(Activity_MyNoHttp.this).inflate(R.layout.news_item_copy, null);
            }

            final ImageView img_head = (ImageView) view.findViewById(R.id.img_head);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
            TextView tv_desc = (TextView) view.findViewById(R.id.tv_desc);

            LinearLayout lay_item = (LinearLayout) view.findViewById(R.id.lay_item_nohttp);

            SwipeLayout  mytext_swip= (SwipeLayout) view.findViewById(R.id.mytext_swip);

            mytext_swip.setShowMode(SwipeLayout.ShowMode.PullOut);
            mytext_swip.addDrag(SwipeLayout.DragEdge.Right,mytext_swip.findViewById(R.id.lay_ringht_view));

            mytext_swip.findViewById(R.id.item_sliding_love).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    CommonUtil.showToask(baseContext,"收藏："+Temp_List.get(position).getTitle());
                }
            });
            mytext_swip.findViewById(R.id.item_sliding_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    CommonUtil.showToask(baseContext,"删除："+Temp_List.get(position).getTitle());

                    Temp_List.remove(position);
                    ktvAdapter.notifyDataSetChanged();
                }
            });
            mytext_swip.getSurfaceView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CommonUtil.showToask(baseContext,"--进入详情--："+Temp_List.get(position).getTitle());

                }
            });

//            lay_item.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    CommonUtil.showToask(baseContext, "进入详情" + Temp_List.get(position).getTitle());
//                }
//            });

            Picasso.with(Activity_MyNoHttp.this).load(Img_Ip + Temp_List.get(position).getLogo1()).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(img_head);
            tv_title.setText(Temp_List.get(position).getTitle());
            tv_date.setText(Temp_List.get(position).getDate());
            tv_desc.setText(Temp_List.get(position).getInfo());
            return view;
        }*/

    }


    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
