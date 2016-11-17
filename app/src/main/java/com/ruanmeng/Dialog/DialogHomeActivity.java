package com.ruanmeng.Dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.ruanmeng.BaseActivity;
import com.ruanmeng.project_model.R;
import com.ruanmeng.utils.T;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogHomeActivity extends BaseActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_home);
        ButterKnife.bind(this);
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        init_title();
        changeTitle("Dialog",null);
        mMenuItems.add(new DialogMenuItem("收藏", R.mipmap.ic_winstyle_favor));
        mMenuItems.add(new DialogMenuItem("下载", R.mipmap.ic_winstyle_download));
        mMenuItems.add(new DialogMenuItem("分享", R.mipmap.ic_winstyle_share));
        mMenuItems.add(new DialogMenuItem("删除", R.mipmap.ic_winstyle_delete));
        mMenuItems.add(new DialogMenuItem("歌手", R.mipmap.ic_winstyle_artist));
        mMenuItems.add(new DialogMenuItem("专辑", R.mipmap.ic_winstyle_album));
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:

                NormalDialogStyleOne();

                break;
            case R.id.btn2:

                ActionSheetDialog();

                break;
            case R.id.btn3:

                NormalDialogCustomAttr();

                break;
            case R.id.btn4:

                DiaogAnimChoose.showAnim(DialogHomeActivity.this);

                break;
            case R.id.btn5:
                NormalListDialog();


                break;
        }
    }



    public void setBasIn(BaseAnimatorSet bas_in) {
        this.mBasIn = bas_in;
    }

    public void setBasOut(BaseAnimatorSet bas_out) {
        this.mBasOut = bas_out;
    }
    private void NormalDialogStyleOne() {
        final NormalDialog dialog = new NormalDialog(this);
        dialog.content("是否确定退出程序?")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        T.showShort(DialogHomeActivity.this, "left");
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        T.showShort(DialogHomeActivity.this, "right");
                        dialog.dismiss();
                    }
                });
    }

    private void ActionSheetDialog() {
        final String[] stringItems = {"接收消息并提醒", "接收消息但不提醒", "收进群助手且不提醒", "屏蔽群消息"};
        final ActionSheetDialog dialog = new ActionSheetDialog(DialogHomeActivity.this, stringItems, null);
        dialog.title("选择群消息提醒方式\r\n(该群在电脑的设置:接收消息并提醒)")//
                .titleTextSize_SP(14.5f)//
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                T.showShort(DialogHomeActivity.this, stringItems[position]);
                dialog.dismiss();
            }
        });
    }

    private void NormalDialogCustomAttr() {
        final NormalDialog dialog = new NormalDialog(DialogHomeActivity.this);
        dialog.isTitleShow(false)//
                .bgColor(Color.parseColor("#383838"))//
                .cornerRadius(5)//
                .content("是否确定退出程序?")//
                .contentGravity(Gravity.CENTER)//
                .contentTextColor(Color.parseColor("#ffffff"))//
                .dividerColor(Color.parseColor("#222222"))//
                .btnTextSize(15.5f, 15.5f)//
                .btnTextColor(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"))//
                .btnPressColor(Color.parseColor("#2B2B2B"))//
                .widthScale(0.85f)//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        T.showShort(DialogHomeActivity.this, "left");
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        T.showShort(DialogHomeActivity.this, "right");
                        dialog.dismiss();
                    }
                });
    }



    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(DialogHomeActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                T.showShort(DialogHomeActivity.this, mMenuItems.get(position).mOperName);
                dialog.dismiss();
            }
        });
    }




}
