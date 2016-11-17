package com.ruanmeng.project_model.lunbo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.nohttp.CallServer;
import com.ruanmeng.nohttp.CustomHttpListener;
import com.ruanmeng.project_model.R;
import com.ruanmeng.share.Const;
import com.ruanmeng.share.HttpIP;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LunboActivity extends BaseActivity {

    @BindView(R.id.ic_fragment_home_lunbo)
    ImageCycleView Lunbo;
    private Request<String> mRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunbo);
        ButterKnife.bind(this);
        getData();
        init_title();
        changeTitle("轮播",null);
        Lunbo.setAutoCycle(false);/////设置是否自动轮播
    }



    public void getData() {
        Request<String> mRequest = NoHttp.createStringRequest(HttpIP.LunBo, Const.POST);
        mRequest.add("service", "Index.IndexCarouse");
        // 添加到请求队列
        CallServer.getRequestInstance().add(this, 0, mRequest,
                new CustomHttpListener(this, true, LunBoInfo.class) {
            /*        @Override
                    public void doWork(Object data, String msgcode) {
                        final List<LunBoInfo.DataBean> list = ((LunBoInfo) data).getData();
                        Lunbo.loadData(list.size(), new ImageCycleView.LoadImageCallBack() {
                            @Override
                            public void loadAndDisplay(ImageView iv, int position) {
//                                if(list.get(position).getImg_path().contains("http")){
//                                    imageloader.DisplayImage(list.get(position).getImg_path1(), iv, false);
                                com.ruanmeng.utils.ImageLoader.showImage("http://qmjs.weiruanmeng.com"+list.get(position).getImg_path(), iv);
//                                }else {
////                                    imageloader.DisplayImage(HttpIp.IpImg+info.getInfo().get(position).getPicturePath(), iv, false);
//                                }



                            }
                        });
                        Lunbo.setOnPageClickListener(new ImageCycleView.OnPageClickListener() {
                            @Override
                            public void onClick(View imageView, int position) {

                            }
                        });
                        Toast.makeText(getApplicationContext(), "请求成功" + data, Toast.LENGTH_SHORT).show();
                    }*/

                    @Override
                    public void doWork(Object data, boolean isOne) {
                        final List<LunBoInfo.DataBean> list = ((LunBoInfo) data).getData();
                        Lunbo.loadData(list.size(), new ImageCycleView.LoadImageCallBack() {
                            @Override
                            public void loadAndDisplay(ImageView iv, int position) {
//                                if(list.get(position).getImg_path().contains("http")){
//                                    imageloader.DisplayImage(list.get(position).getImg_path1(), iv, false);
                                    com.ruanmeng.utils.ImageLoader.showImage("http://qmjs.weiruanmeng.com"+list.get(position).getImg_path(), iv);
//                                }else {
////                                    imageloader.DisplayImage(HttpIp.IpImg+info.getInfo().get(position).getPicturePath(), iv, false);
//                                }



                            }
                        });
                        Lunbo.setOnPageClickListener(new ImageCycleView.OnPageClickListener() {
                            @Override
                            public void onClick(View imageView, int position) {

                            }
                        });
                        Toast.makeText(getApplicationContext(), "请求成功" + data, Toast.LENGTH_SHORT).show();
                    }
                }, true, true);

    }



}
