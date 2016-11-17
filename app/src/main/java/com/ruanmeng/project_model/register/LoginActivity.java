package com.ruanmeng.project_model.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.ruanmeng.BaseActivity;
import com.ruanmeng.project_model.R;
import com.ruanmeng.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_login_name)
    EditText et_name;
    @BindView(R.id.et_login_pwd)
    EditText et_pwd;
    @BindView(R.id.btn_login_denglu)
    Button bt_login;
    @BindView(R.id.tv_login_forget)
    TextView tv_forget;
    @BindView(R.id.tv_login_signup)
    TextView tv_sign;
    @BindView(R.id.lay_acc_pw)
    LinearLayout layAccPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init_title();
        changeTitle("登录", null);
    }

    @Override
    public void init_title() {
        super.init_title();

        bt_login.setBackgroundResource(R.drawable.rec_bg_gray);
        bt_login.setClickable(false);

        et_name.addTextChangedListener(this);
        et_pwd.addTextChangedListener(this);

    }

    @Override
    public void doClick(View v) {
        super.doClick(v);
        switch (v.getId()) {
            case R.id.tv_login_forget:
                startActivity(ForgetActivity.class);
                break;
            case R.id.tv_login_signup:
                startActivity(RegisterActivity.class);
                break;
            case R.id.btn_login_denglu:
                String name = et_name.getText().toString().trim();
                final String pwd = et_pwd.getText().toString().trim();
                if (!CommonUtil.isMobileNumber(name)) {
                    CommonUtil.showToask(this, "手机号码格式错误");
                    showAnmintion();

                    return;
                }
                if (pwd.length() < 6) {
                    CommonUtil.showToask(this, "密码长度不少于6位");
                    showAnmintion();
                    return;
                }


                break;
        }
    }


    /**
     * 需要监听 edittext的 变化
     *
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!TextUtils.isEmpty(et_name.getText().toString())
                && !TextUtils.isEmpty(et_pwd.getText().toString())) {
            bt_login.setBackgroundResource(R.drawable.rec_bg_blue);
            bt_login.setClickable(true);
        } else {
            bt_login.setBackgroundResource(R.drawable.rec_bg_gray);
            bt_login.setClickable(false);
        }
    }


    /**
     * 显示出错的动画效果
     */
    public void showAnmintion() {
        //                 动画效果
        YoYo.with(Techniques.Shake).duration(700).playOn(layAccPw);
    }

}
