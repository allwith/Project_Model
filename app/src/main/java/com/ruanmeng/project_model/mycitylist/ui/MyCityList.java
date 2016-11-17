package com.ruanmeng.project_model.mycitylist.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;

import com.ruanmeng.nohttp.CallServer;
import com.ruanmeng.nohttp.CustomHttpListener;
import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.guide.CityM;
import com.ruanmeng.project_model.mycitylist.city.CityAdapter;
import com.ruanmeng.project_model.mycitylist.city.CityEntity;
import com.ruanmeng.utils.CommonUtil;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.indexablelistview.IndexEntity;
import me.yokeyword.indexablelistview.IndexHeaderEntity;
import me.yokeyword.indexablelistview.IndexableStickyListView;


public class MyCityList extends AppCompatActivity {
    private SearchView mMySearchview;
    private IndexableStickyListView mMySview;

    //    noHttp  网络请求
    public final static String Urlstring = "http://kge.gexiazi.com/tools/Interface.ashx";
    private Activity baseContext;
    private Request<String> mRequest;


    //     城市集合 获取到数据之后 保存到这个 集合中
    private List<CityEntity> mlist = new ArrayList<>();

    //    热门城市
    private String[] mHotCities = new String[]{"登封市", "告城市", "八方市", "嵩山市"};
    //    gps定位城市  和热门城市
    IndexHeaderEntity<CityEntity> gpsHeader, hotHeader;

    //     设置全局以便 获取定位信息之后修改当前位置
    CityEntity gpscitybeans = new CityEntity();


    //    IndexableStickyListView  的 适配器
    private CityAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_city_list);

        baseContext = this;
        initView();
        getIndexData();


    }

    private void getIndexData() {
        mRequest = NoHttp.createStringRequest(Urlstring, RequestMethod.POST);
        mRequest.add("action", "allcity");
        mRequest.add("version", "1.0.1");

        CallServer.getRequestInstance().add(baseContext, 0, mRequest, new CustomHttpListener(baseContext, true, CityM.class) {
            @Override
            public void doWork(Object data, boolean isOne) {
                String textname = "";
                for (int i = 0; i < ((CityM) data).getData().size(); i++) {
                    textname = ((CityM) data).getData().get(i).getName();
//                     多音字处理
                    String firstLetter = textname.substring(0, 1);
                    if (!TextUtils.isEmpty(firstLetter)) {
                        switch (firstLetter) {
                            case "长":
                                textname = "#chang#" + textname;
                                break;
                            case "重":
                                textname = "#chong#" + textname;
                        }
                    }
                    CityEntity mce = new CityEntity();
                    mce.setName(textname);
                    mlist.add(mce);
                }

                initGPSCity();
                initHOTCity();
                mMySview.bindDatas(mlist, gpsHeader, hotHeader);

            }

            @Override
            public void onFinally(JSONObject obj) {
                super.onFinally(obj);

            }
        }, true, true);


    }

    //      定位城市
    private void initGPSCity() {
        ArrayList<CityEntity> gpsCitys = new ArrayList<>();
        gpscitybeans.setName("定位中....");
        gpsCitys.add(gpscitybeans);
        gpsHeader = new IndexHeaderEntity<>("定", "GPS自动定位", gpsCitys);

    }

    //     热门城市
    private void initHOTCity() {

        hotHeader = new IndexHeaderEntity<>();
        ArrayList<CityEntity> hotCityList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            CityEntity hotcity = new CityEntity();
            hotcity.setName(mHotCities[i]);
            hotCityList.add(hotcity);
        }
        hotHeader.setHeaderTitle("热门城市");
        hotHeader.setIndex("热");
        hotHeader.setHeaderList(hotCityList);

    }

    private void initView() {
        setTitle("城市列表");
        mMySearchview = (SearchView) findViewById(R.id.my_searchview);
        mMySview = (IndexableStickyListView) findViewById(R.id.my_sview);

//         v设置适配器
        mAdapter = new CityAdapter(baseContext);
        mMySview.setAdapter(mAdapter);


//        搜索view的监听
        mMySearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//        当输入内容改变
//        委托搜索处理
//        返回值为 true

                mMySview.searchTextChange(newText);
                return true;
            }
        });


//        城市点击事件
        mMySview.setOnItemContentClickListener(new IndexableStickyListView.OnItemContentClickListener() {
            @Override
            public void onItemClick(View v, IndexEntity indexEntity) {

                CityEntity mcity = (CityEntity) indexEntity;
                CommonUtil.showToask(baseContext, mcity.getName() + "");

            }
        });

//         缩写条目点击 事件
        mMySview.setOnItemTitleClickListener(new IndexableStickyListView.OnItemTitleClickListener() {
            @Override
            public void onItemClick(View v, String title) {
                CommonUtil.showToask(baseContext, title + "");
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gpscitybeans.setName("李福昌");
                mAdapter.notifyDataSetChanged();
            }
        }, 3000);
    }
    /**
     * 1   IndexableStickyListView  moudle 导入
     * 2  //    城市列表     compile project(':indexablelistview')  导入
     * 3         获取到数据之后  转换为  CityEntity 并添加到集合中
     * 4     设置两个顶部的 header
     * 5 最后绑定数据   mMySview.bindDatas(mlist,gpsHeader,hotHeader);
     *
     *
     */

}
