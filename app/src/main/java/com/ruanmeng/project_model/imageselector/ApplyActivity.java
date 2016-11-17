package com.ruanmeng.project_model.imageselector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.ruanmeng.BaseActivity;
import com.ruanmeng.project_model.R;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.lijunguan.imgselector.ImageSelector;

public class ApplyActivity extends BaseActivity {

    @BindView(R.id.lv_apply_list)
    RecyclerView mRecyclerView;

    private List<String> imagesPath = new ArrayList<>();
    private ApplyAdapter adapter;

    private LinearLayout lay_showmsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        ButterKnife.bind(this);
        init_title();
        changeTitle("选择图片", null);
    }

    @Override
    public void init_title() {
        super.init_title();

        lay_showmsg = (LinearLayout) findViewById(R.id.lay_showmsg);
//        1
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));


//        2
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);


        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        imagesPath.add("-1");
        adapter = new ApplyAdapter(this, R.layout.item_apply_img, imagesPath);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelector.REQUEST_SELECT_IMAGE) {
            if (resultCode == RESULT_OK)
            {
                ArrayList<String> images = data.getStringArrayListExtra(ImageSelector.SELECTED_RESULT);
                if (images != null)
                {
                    if (imagesPath.contains("-1"))
                        imagesPath.remove("-1");

                    imagesPath.addAll(images);

                    if (imagesPath.size() < 3)
                        imagesPath.add("-1");


//                    if (imagesPath.size()==1){
//                        lay_showmsg.setVisibility(View.VISIBLE);
//                    }

//                    for (int i = 0; i < imagesPath.size(); i++) {
//                        Log.d("--lfc", "图片" + i + "地址为:" + imagesPath.get(i));
//                    }
                    adapter.notifyDataSetChanged();
                }
                if (imagesPath.size()==1){
                    lay_showmsg.setVisibility(View.VISIBLE);
                }else {
                    lay_showmsg.setVisibility(View.GONE);
                }
            }
        }
    }

    private class ApplyAdapter extends CommonAdapter<String> {

//        List<String> mlist;

        public ApplyAdapter(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);
//            mlist = datas;
        }

        @Override
        public void convert(final ViewHolder holder, final String info)
        {
            ImageView iv_img = holder.getView(R.id.iv_item_apply_img);
            ImageView iv_del = holder.getView(R.id.iv_item_apply_img_del);

            if (TextUtils.equals("-1", info)) {
                iv_del.setVisibility(View.INVISIBLE);
                iv_img.setImageResource(R.drawable.loan_photo_x2);

//                Log.d("--lfc", "mDatas 的size 为：" + mDatas.size());
//                for (int i = 0; i < mDatas.size(); i++) {
//                    Log.d("--lfc", "图片" + i + "地址为:" + mDatas.get(i));
//                }

                //                 显示是否添加三张
//                if (mDatas.size() == 1) {
//                    lay_showmsg.setVisibility(View.VISIBLE);
//                } else {
//                    lay_showmsg.setVisibility(View.GONE);
//                }

            } else {
                iv_del.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(info).into(iv_img);
            }

            iv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDatas.remove(info);
                    if (!mDatas.contains("-1"))
                        mDatas.add("-1");

                    if (mDatas.size()==1){
                        lay_showmsg.setVisibility(View.VISIBLE);
                    }

                    notifyDataSetChanged();
                }
            });

            iv_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TextUtils.equals("-1", info)) {
//                        lay_showmsg.setVisibility(View.GONE);

                        ImageSelector imageSelector = ImageSelector.getInstance();
                        imageSelector.setSelectModel(ImageSelector.MULTI_MODE);
                        imageSelector.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorAccent));
                        imageSelector.setShowCamera(true);
                        imageSelector.setMaxCount(3 - holder.getLayoutPosition());
                        imageSelector.setGridColumns(3);
                        imageSelector.startSelect(mContext);
                    }
                }
            });

        }
    }

}
