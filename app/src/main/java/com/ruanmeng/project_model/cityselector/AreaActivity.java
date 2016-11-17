package com.ruanmeng.project_model.cityselector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.cityselector.sort.CharacterParser;
import com.ruanmeng.project_model.cityselector.sort.CityActivity;
import com.ruanmeng.project_model.cityselector.sort.PinyinComparator;
import com.ruanmeng.project_model.cityselector.sort.SideBar;
import com.ruanmeng.share.Const;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.demo.CallServer;
import com.yolanda.nohttp.demo.CustomHttpListener;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.support.SectionAdapter;
import com.zhy.base.adapter.recyclerview.support.SectionSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AreaActivity extends BaseActivity {

    @BindView(R.id.lv_area_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_area_dialog)
    TextView tv_dialog;
    @BindView(R.id.sb_area_sidebar)
    SideBar mSideBar;

    private CityAdapter adapter;
    private List<CityModel.CityInfo> list;
    private List<CityModel.CityInfo> mAllLists = new ArrayList<>();
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
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

        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mRecyclerView.scrollToPosition(position);
                }
            }
        });

        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        mSideBar.setTextView(tv_dialog);
    }

    private void getData() {
        mRequest = NoHttp.createStringRequest("http://api.zzwms.com/public/selectprovince", Const.POST);
        // 添加到请求队列
        CallServer.getRequestInstance().add(baseContext, 0, mRequest,
                new CustomHttpListener(baseContext, true, CityModel.class) {
                    @Override
                    public void doWork(Object data, String code) {

                        // 启动动画的关键位, 顺次添加动画效果
                        list = ((CityModel) data).getData().getProvince();
                        seperateLists(list);

                        // 排序时，使用 adapter.notifyDataSetChanged() 头标题不显示
                        adapter = new CityAdapter(baseContext, R.layout.item_area_list, mAllLists, sectionSupport);
                        mRecyclerView.setAdapter(adapter);

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

    private void seperateLists(List<CityModel.CityInfo> mlist) {
        if (mlist != null && mlist.size() > 0) {
            for (int i = 0; i < mlist.size(); i++) {
                CityModel.CityInfo entity = new CityModel.CityInfo();
                entity.setNid(mlist.get(i).getNid());
                entity.setTitle(mlist.get(i).getTitle());
                String pinyin = characterParser.getSelling(mlist.get(i).getTitle());
                String sortString = pinyin.substring(0, 1).toUpperCase();

                if (sortString.matches("[A-Z]")) {
                    if (TextUtils.equals("重庆市", mlist.get(i).getTitle())) {
                        entity.setSortLetters("C");
                    } else {
                        entity.setSortLetters(sortString.toUpperCase());
                    }
                } else {
                    entity.setSortLetters("#");
                }
                mAllLists.add(entity);
                Collections.sort(mAllLists, pinyinComparator);
            }
        }
    }

    SectionSupport<CityModel.CityInfo> sectionSupport = new SectionSupport<CityModel.CityInfo>() {
        @Override
        public int sectionHeaderLayoutId() {
            return R.layout.sort_header;
        }

        @Override
        public int sectionTitleTextViewId() {
            return R.id.tv_sort_head_hint;
        }

        @Override
        public String getTitle(CityModel.CityInfo info) {
            return info.getSortLetters();
        }
    };

    private class CityAdapter extends SectionAdapter<CityModel.CityInfo> {

        public CityAdapter(Context context, int layoutId,
                           List<CityModel.CityInfo> datas, SectionSupport sectionSupport) {
            super(context, layoutId, datas, sectionSupport);
        }

        @Override
        public void convert(ViewHolder holder, final CityModel.CityInfo info) {
            holder.setText(R.id.tv_brand_item_title, info.getTitle());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(baseContext, CityActivity.class);
                    intent.putExtra("id_province", info.getNid());
                    intent.putExtra("title_province", info.getTitle());
                    startActivityForResult(intent, 100);
                }
            });
        }

        public int getPositionForSection(char section) {
            for (int i = 0; i < mAllLists.size(); i++) {
                String sortStr = mAllLists.get(i).getSortLetters();
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == section) {
                    return i;
                }
            }
            return -1;
        }
    }

}
