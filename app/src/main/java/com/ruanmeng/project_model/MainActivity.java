package com.ruanmeng.project_model;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruanmeng.Dialog.DialogHomeActivity;
import com.ruanmeng.project_model.CeHuaShanChu.DefineSlidingDeleteActivity;
import com.ruanmeng.project_model.DragTopLayout.DragTopActivity;
import com.ruanmeng.project_model.Map.MapActivity;
import com.ruanmeng.project_model.MyLoadingView.MyLoadingView;
import com.ruanmeng.project_model.Refresh.NewsActivity;
import com.ruanmeng.project_model.ScrollViewHeader.ScrollviewHeaderActivity;
import com.ruanmeng.project_model.ScrollViewHeader.SpringScrollView;
import com.ruanmeng.project_model.TableLayout.TableLayoutActivity;
import com.ruanmeng.project_model.Timer.TimerActivity;
import com.ruanmeng.project_model.TransparentToolbar.TransParentToolbarActivity;
import com.ruanmeng.project_model.UpLoadPic.JGHeadImgActivity;
import com.ruanmeng.project_model.ViewPager.myui.MyViewpager;
import com.ruanmeng.project_model.calendar.CalendarActivity;
import com.ruanmeng.project_model.cityselector.SelectActivity;
import com.ruanmeng.project_model.gitview.MyGitVIew;
import com.ruanmeng.project_model.guide.demo.Demo_Activity;
import com.ruanmeng.project_model.imageselector.ApplyActivity;
import com.ruanmeng.project_model.likebutton.Activity_LikeButton;
import com.ruanmeng.project_model.lunbo.PictureLunBoActivity;
import com.ruanmeng.project_model.myedittext.MyEdittext_Activity;
import com.ruanmeng.project_model.mylockview.myui.MyOperation_Activity;
import com.ruanmeng.project_model.mynetwork.Activity_NetText1;
import com.ruanmeng.project_model.mynohttp.Activity_MyNoHttp;
import com.ruanmeng.project_model.myrecyclerview.ui.MyRecyclerView;
import com.ruanmeng.project_model.myselectview.myui.Activity_MySelect;
import com.ruanmeng.project_model.myzoomview.Text_ZoomActivity;
import com.ruanmeng.project_model.orderlist.OrderActivity;
import com.ruanmeng.project_model.register.LoginActivity;
import com.ruanmeng.project_model.report.ReportActivity;
import com.ruanmeng.project_model.shopping.ShopActivity;
import com.ruanmeng.project_model.slideback.Activity_TextBack;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SpringScrollView.OnScrollListener {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.button7)
    Button button7;
    @BindView(R.id.button11)
    Button button11;
    @BindView(R.id.button12)
    Button button12;
    @BindView(R.id.button13)
    Button button13;
    @BindView(R.id.button14)
    Button button14;
    @BindView(R.id.button15)
    Button button15;
    @BindView(R.id.button16)
    Button button16;
    @BindView(R.id.button17)
    Button button17;
    @BindView(R.id.button18)
    Button button18;
    @BindView(R.id.button19)
    Button button19;
    @BindView(R.id.button20)
    Button button20;
    @BindView(R.id.button21)
    Button button21;
    @BindView(R.id.button22)
    Button button22;
    @BindView(R.id.button23)
    Button button23;
    @BindView(R.id.button24)
    Button button24;
    @BindView(R.id.button25)
    Button button25;
    @BindView(R.id.button26)
    Button button26;
    @BindView(R.id.myScroll)
    SpringScrollView myScroll;
    @BindView(R.id.invis)
    TextView invis;
    @BindView(R.id.tv_nav_back)
    TextView tvNavBack;
    @BindView(R.id.tv_nav_title)
    TextView tvNavTitle;
    @BindView(R.id.tv_nav_right)
    TextView tvNavRight;
    @BindView(R.id.ll_titles)
    RelativeLayout llTitles;
    @BindView(R.id.button27)
    Button button27;
    @BindView(R.id.button28)
    Button button28;
    @BindView(R.id.button29)
    Button button29;
    @BindView(R.id.button30)
    Button button30;
    @BindView(R.id.button31)
    Button button31;
    @BindView(R.id.button32)
    Button button32;
    @BindView(R.id.button33)
    Button button33;
    @BindView(R.id.button34)
    Button button34;
    @BindView(R.id.button35)
    Button button35;
    @BindView(R.id.button36)
    Button button36;
    @BindView(R.id.button37)
    Button button37;
    @BindView(R.id.button38)
    Button button38;


    //    private TextView invis;
//    private SpringScrollView sc;
    private RelativeLayout title_layout;
//    private Toolbar toolbar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        init_title();
//        changeTitle("主页", null);
//        title_layout = (RelativeLayout) findViewById(R.id.ll_titles);
//        toolbar = (Toolbar)findViewById(R.id.toolbars);
//        invis = (TextView) findViewById(R.id.invis);
//        sc = (SpringScrollView) findViewById(R.id.myScroll);
        myScroll.setOnScrollListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, ApplyActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, SelectActivity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(this, OrderActivity.class));
                break;
            case R.id.button7:
                startActivity(new Intent(this, CalendarActivity.class));
                break;
            case R.id.button11:
                startActivity(new Intent(this, ReportActivity.class));
//                startActivity(ReportActivity.class);
                break;
            case R.id.button12:
                startActivity(new Intent(this, ShopActivity.class));
//                startActivity(ShopActivity.class);
                break;
            case R.id.button13:
                startActivity(new Intent(this, NewsActivity.class));
//                startActivity(NewsActivity.class);
                break;
            case R.id.button14:
                startActivity(new Intent(this, DialogHomeActivity.class));
//                startActivity(DialogHomeActivity.class);
                break;
            case R.id.button15:
                startActivity(new Intent(this, PictureLunBoActivity.class));
//                startActivity(LunboActivity.class);
                break;
            case R.id.button16:
                startActivity(new Intent(this, TimerActivity.class));
//                startActivity(TimerActivity.class);
                break;
            case R.id.button17:
                startActivity(new Intent(this, DragTopActivity.class));
//                startActivity(DragTopActivity.class);
                break;
            case R.id.button18:
                startActivity(new Intent(this, TransParentToolbarActivity.class));
//                startActivity(TransParentToolbarActivity.class);
                break;
            case R.id.button19:
                startActivity(new Intent(this, ScrollviewHeaderActivity.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button20:
                startActivity(new Intent(this, TableLayoutActivity.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button21:
                startActivity(new Intent(this, DefineSlidingDeleteActivity.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button22:
                startActivity(new Intent(this, JGHeadImgActivity.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button23:
                startActivity(new Intent(this, MapActivity.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button24:
                startActivity(new Intent(this, ApplyActivity.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button25:
                startActivity(new Intent(this, Activity_MyNoHttp.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;

            case R.id.button26:
//                startActivity(new Intent(this,Activity_MyNoHttp.class));
//                startActivity(ScrollviewHeaderActivity.class);
                doboom();
                break;
            case R.id.button27:
                startActivity(new Intent(this, Activity_NetText1.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button28:
                startActivity(new Intent(this, Text_ZoomActivity.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button29:
                startActivity(new Intent(this, Demo_Activity.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button30:
                startActivity(new Intent(this, MyEdittext_Activity.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button31:
                startActivity(new Intent(this, Activity_LikeButton.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button32:
                startActivity(new Intent(this, Activity_TextBack.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button33:
                startActivity(new Intent(this, MyOperation_Activity.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button34:
                startActivity(new Intent(this, Activity_MySelect.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button35:

//                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, button35, "shareView");
//                startActivity(new Intent(this, MyGitVIew.class), activityOptions.toBundle());
                startActivity(new Intent(this, MyGitVIew.class));
                break;
            case R.id.button36:
                startActivity(new Intent(this, MyRecyclerView.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button37:
                startActivity(new Intent(this, MyViewpager.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;
            case R.id.button38:
                startActivity(new Intent(this, MyLoadingView.class));
//                startActivity(ScrollviewHeaderActivity.class);
                break;


        }
    }


    //    模拟异常崩溃
    private void doboom() {
        String str = null;
        Log.d("log", str);


    }

    public void MyTest() {

        // TODO: 2016/11/14   跳转文件管理器页面
//
        final int REQUEST_CODE_SELECT_IMAGE = 1;
        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
        openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "file/*");
        startActivityForResult(openAlbumIntent, REQUEST_CODE_SELECT_IMAGE);


//        -------------------------------------------------------------
        //                通讯录
//                Intent i = new Intent(Intent.ACTION_PICK);
//                i.setType("vnd.android.cursor.dir/phone");
//                startActivityForResult(i, 0);

//        ----------------------------------------------------------------
        //       Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);
//                startActivity(intent);


    }


    @Override
    public void onScroll(int deltaY) {
        if (deltaY < 0) {
            //下拉 显示头部
//            title_layout.setVisibility(View.GONE);
            llTitles.setVisibility(View.GONE);
        } else {

            // 上拉 不显示头部
//            title_layout.setVisibility(View.VISIBLE);
            llTitles.setVisibility(View.VISIBLE);
        }
    }


}
