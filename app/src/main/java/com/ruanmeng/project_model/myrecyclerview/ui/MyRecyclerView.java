package com.ruanmeng.project_model.myrecyclerview.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ruanmeng.animator.ItemAnimatorFactory;
import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.myrecyclerview.adapter.CommonRecyclerViewAdapter;
import com.ruanmeng.project_model.myrecyclerview.adapter.CommonRecyclerViewHolder;
import com.ruanmeng.project_model.myrecyclerview.bgarefresh.bgarefreshlayoutviewholder.DefineBAGRefreshWithLoadView;
import com.ruanmeng.project_model.myrecyclerview.mydivider.MyItemDivider;
import com.ruanmeng.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class MyRecyclerView extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private BGARefreshLayout myRefresh;
    private RecyclerView myRecyclerview;

    private Activity mContext;

    //  数据集合
    private List<String> mList_Data = new ArrayList<>();

    //     适配器
    private CommonRecyclerViewAdapter<String> mAdapter = null;

    private LinearLayoutManager mLM;


    //     设置刷新和加载
    private com.ruanmeng.project_model.myrecyclerview.bgarefresh.bgarefreshlayoutviewholder.DefineBAGRefreshWithLoadView mDefineBAGRefreshWithLoadView = null;


    //     标记值
    private final static int RESULT_REFRESH = 10086;
    private final static int RESULT_LOAD = RESULT_REFRESH + 1;
    private final static int RESULT_TEST = RESULT_LOAD + 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view);
        mContext = this;


        initView();
        setBgaRefreshLayout();

//        initData();

    }


    //      添加数据
    private void initData() {
        List<String> list_test = new ArrayList<>();
        int pos = mAdapter.getItemCount();
        String string = "";
        for (int t = 0; t < 10; t++) {
            string = "我是第" + t + "项";
            list_test.add(string);
        }
        mList_Data.addAll(list_test);
        if (mAdapter != null) {
//            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemRangeChanged(pos, mList_Data.size());
//            mAdapter.notifyItemInserted(0);
//            notifyItemRangeInserted
        }
    }

    public void AddData() {
        List<String> list_test = new ArrayList<>();
        int pos = mAdapter.getItemCount();
        String string = "";
        for (int t = mList_Data.size(); t < mList_Data.size() + 10; t++) {
            string = "我是第" + t + "项";
            list_test.add(string);
        }
        mList_Data.addAll(list_test);
        if (mAdapter != null) {
//            mAdapter.notifyDataSetChanged();
            Log.d("--lfc", "AddData---mAdapter");
            mAdapter.notifyItemRangeInserted(pos, mList_Data.size());
//            mAdapter.notifyItemInserted(pos);
//            mAdapter.notifyItemRangeInserted();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        myRefresh.beginRefreshing();
        onBGARefreshLayoutBeginRefreshing(myRefresh);

    }

    /**
     * 设置 BGARefreshLayout刷新和加载
     */
    private void setBgaRefreshLayout() {
        mDefineBAGRefreshWithLoadView = new DefineBAGRefreshWithLoadView(mContext, true, true);
//        设置刷新样式
        myRefresh.setRefreshViewHolder(mDefineBAGRefreshWithLoadView);

//         设置尾部的 刷新  text 内容
        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("正在加载中...");

    }

    private void initView() {
        myRefresh = (BGARefreshLayout) findViewById(R.id.my_refresh);
        myRecyclerview = (RecyclerView) findViewById(R.id.my_recyclerview);


//    设置recyclerview 样式                                是否 反序
        mLM = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        myRecyclerview.setLayoutManager(mLM);


//   设置分割线的颜色和 粗细
        MyItemDivider myItemDivider = new MyItemDivider(MyItemDivider.VERTICAL);
        myItemDivider.setSize(1);
        //        0xff  代表#  符号
//        mdivider.setColor(0xFFff0000);
        myItemDivider.setColor(R.color.material_yellow);

        myRecyclerview.addItemDecoration(myItemDivider);
//        myRecyclerview.remov

//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        myRecyclerview.setHasFixedSize(true);

        // TODO: 2016/10/28   设置进场动画
//        myRecyclerview.setItemAnimator(new SlideInOutBottomItemAnimator(myRecyclerview));
//        myRecyclerview.setItemAnimator(new DefaultItemAnimator());
        myRecyclerview.setItemAnimator(ItemAnimatorFactory.slidein());


//        适配器
        mAdapter = new CommonRecyclerViewAdapter<String>(mContext, mList_Data) {
            @Override
            public void convert(CommonRecyclerViewHolder h, String entity, final int position) {
                int item_type = getItemType(position);
                // TODO: 2016/10/28   绑定数据   第二个参数 String entity  是数据集合中的元素 可能为实体类型
                h.setTvText(R.id.tv_name, entity);
                h.setBtnText(R.id.bt, "第" + position + "个按钮");
                if (item_type == 1) {
                    h.setImage(R.id.img_head, getResources().getDrawable(R.drawable.icon2));
                    h.setBtnBg_Color(R.id.bt, R.color.material_green);
                }
                if (item_type == 2) {
                    h.setImage(R.id.img_head, getResources().getDrawable(R.drawable.ic_launcher));
//                    h.setBtnBg_Drawable(R.id.bt, R.drawable.btn_normal);
                    h.setBtnBg_Color(R.id.bt, R.color.material_red);
                }
                if (item_type == 3) {
                    h.setImage(R.id.img_head, getResources().getDrawable(R.drawable.me));
//                    h.setBtnBg_Resources(R.id.bt,R.drawable);
                }

                // TODO: 2016/10/26      设置点击事件的方法       1  找到这个控件设置点击
                // TODO: 2016/10/26                              2  设置 setOnViewInItemClickListener 追加 视图ID
                // TODO: 2016/10/26                              3  设置 setOnRecyclerViewItemClickListener
                ((ImageView) h.getView(R.id.img_head)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CommonUtil.showToask(mContext, "点击了" + position + "图片");
                    }
                });


//                 整个条目点击
//                ((LinearLayout) h.getView(R.id.lay_totole)).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        CommonUtil.showToask(mContext, "点击了" + position);
//                    }
//                });

            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_recyclerview;
            }

            @Override
            public int getItemType(int position) {
//                return super.getItemType(position);

                if (position % 3 == 0) {
                    return 1;
                } else if (position % 3 == 1) {
                    return 2;
                } else {

                    return 3;
                }
            }
        };
        View headview = LayoutInflater.from(MyRecyclerView.this).inflate(R.layout.myhead_view, myRefresh, false);
        Button btn_00 = (Button) headview.findViewById(R.id.btn_mydemo);
        btn_00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showToask(mContext, "这个是头布局的按钮，点一下看会不会怀孕！");
            }
        });

        mAdapter.addHeaderView(headview);

        myRecyclerview.setAdapter(mAdapter);

//        myRecyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            boolean isShowTop = false;
//            boolean isShowBottom = false;
//
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                if (mLM.findLastCompletelyVisibleItemPosition() == mList_Data.size() - 1) {
//
//                    if (!isShowTop) {
//                        CommonUtil.showToask(mContext, "滑动到底部");
//                    }
//                    isShowTop = true;
//                } else {
//
//                    isShowTop = false;
//                }
//
//
//                if (mLM.findFirstCompletelyVisibleItemPosition() == 0) {
//                    if (!isShowBottom) {
//                        CommonUtil.showToask(mContext, "滑动到顶部");
//                    }
//                    isShowBottom = true;
//                } else {
//                    isShowBottom = false;
//                }
//            }
//        });


//          条目点击事件
        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                CommonUtil.showToask(mContext, "点击了" + position);
            }
        });

        mAdapter.setOnViewInItemClickListener(new CommonRecyclerViewAdapter.OnViewInItemClickListener() {
            @Override
            public void onViewInItemClick(View v, int position) {
                CommonUtil.showToask(mContext, "点击了" + position + "个按钮");
            }
        }, R.id.bt);


//        设置刷新和加载的监听
        myRefresh.setDelegate(this);


    }


    /**
     * 刷新
     *
     * @param refreshLayout
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("我草，看你是什么鬼！");
        mDefineBAGRefreshWithLoadView.showLoadingMoreImg();

        if (mList_Data.size() > 0) {
            mList_Data.clear();
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        }

        mHandler.sendEmptyMessageDelayed(RESULT_REFRESH, 2000);
    }

    /**
     * 加载
     *
     * @param refreshLayout
     * @return
     */
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        /** 隐藏图片 **/
//        mDefineBAGRefreshWithLoadView.hideLoadingMoreImg();
//        mDefineBAGRefreshWithLoadView.showLoadingMoreImg();

//        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("正在拼命加载中...");
        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("坑爹呢这是!!！...");

        mHandler.sendEmptyMessageDelayed(RESULT_LOAD, 2000);

        return true;
    }


    /**
     * 模拟请求网路数据
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case RESULT_REFRESH:

                    initData();
                    myRefresh.endRefreshing();

                    break;

                case RESULT_LOAD:

                    AddData();

                    myRefresh.endLoadingMore();
                    break;

                case RESULT_TEST:

                    break;
            }

        }
    };
}
