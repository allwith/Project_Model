package com.ruanmeng.project_model.likebutton;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.likebutton.view.LikeButton;
import com.ruanmeng.project_model.likebutton.view.OnLikeListener;
import com.ruanmeng.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity_LikeButton extends AppCompatActivity implements OnLikeListener, View.OnClickListener {

    @BindView(R.id.heart_button)
    LikeButton heartButton;
    @BindView(R.id.thumb_button)
    LikeButton thumbButton;
    @BindView(R.id.btn_text_likebutton)
    Button btnTextLikebutton;


    private Activity  basecontext;


//    点击的次数
    private   long  clickcounts=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_button);
        ButterKnife.bind(this);
        basecontext=this;
        init();
    }

    private void init() {
        heartButton.setOnLikeListener(this);
        thumbButton.setOnLikeListener(this);
        btnTextLikebutton.setOnClickListener(this);
    }

    @Override
    public void liked(LikeButton likeButton)
    {

            int  btn_id=likeButton.getId();
        switch (btn_id){
            case  R.id.heart_button:
                CommonUtil.showToask(basecontext,"关注");
                break;

            case  R.id.thumb_button:
                CommonUtil.showToask(basecontext,"点赞");

                break;

        }
    }

    @Override
    public void unLiked(LikeButton likeButton) {

        int  btn_id=likeButton.getId();
        switch (btn_id){
            case  R.id.heart_button:
                CommonUtil.showToask(basecontext,"取消关注");
                break;

            case  R.id.thumb_button:
                CommonUtil.showToask(basecontext,"取消点赞");

                break;

        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()){
            case  R.id.btn_text_likebutton:
                clickcounts++;

                if (clickcounts%2==0){
                    CommonUtil.showToask(basecontext,"普通按钮--  喜欢");
                    btnTextLikebutton.setText(clickcounts+"  喜欢");
                }else {
                    CommonUtil.showToask(basecontext,"普通按钮--  不喜欢");
                    btnTextLikebutton.setText(clickcounts+"  不喜欢");

                }

                break;
        }

    }
}
