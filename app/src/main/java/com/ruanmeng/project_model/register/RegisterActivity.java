package com.ruanmeng.project_model.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.project_model.R;
import com.ruanmeng.share.Const;
import com.ruanmeng.utils.CommonUtil;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.demo.CallServer;
import com.yolanda.nohttp.demo.CustomHttpListener;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_register_account)
    EditText et_name;
    @BindView(R.id.et_register_yzm)
    EditText et_yzm;
    @BindView(R.id.btn_register_yzm)
    Button bt_yzm;
    @BindView(R.id.et_register_pwd)
    EditText et_pwd;
    @BindView(R.id.btn_register_signup)
    Button bt_sing;

    // 验证码
    private int time_count;
    private Runnable thread;
    private String tel;
    private String YZM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init_title();
        changeTitle("注册", null);
    }

    @Override
    public void init_title() {
        super.init_title();

        bt_sing.setBackgroundResource(R.drawable.rec_bg_gray);
        bt_sing.setClickable(false);

        et_name.addTextChangedListener(this);
        et_yzm.addTextChangedListener(this);
        et_pwd.addTextChangedListener(this);
    }

    @Override
    public void doClick(View v) {
        super.doClick(v);
        final String name = et_name.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btn_register_yzm:
                if (TextUtils.isEmpty(name)) {
                    CommonUtil.showToask(this, "请输入手机号");
                    return;
                }
                if (!CommonUtil.isMobileNumber(name)) {
                    CommonUtil.showToask(this, "手机号格式不正确");
                    return;
                }
                time_count = 60;
                thread = new Runnable() {
                    public void run() {
                        bt_yzm.setText("(" + time_count + ")秒后重发");
                        if (time_count > 0) {
                            bt_yzm.postDelayed(thread, 1000);
                            time_count--;
                        } else {
                            bt_yzm.setText("获取验证码");
                            bt_yzm.setBackgroundResource(R.drawable.rec_stroke_grey);
                            bt_yzm.setClickable(true);
                            time_count = 60;
                        }
                    }
                };

                mRequest = NoHttp.createStringRequest("", Const.POST);
                mRequest.add("mobile", name);
                // 添加到请求队列
                CallServer.getRequestInstance().add(this, 0, mRequest,
                        new CustomHttpListener(baseContext, true, null) {
                            @Override
                            public void doWork(Object data, String code) {
                                try {
                                    JSONObject obj = ((JSONObject) data).getJSONObject("data");
                                    bt_yzm.setBackgroundResource(R.drawable.rec_stroke_red);
                                    bt_yzm.setClickable(false);
                                    bt_yzm.post(thread);
                                    YZM = obj.getString("code");
                                    tel = obj.getString("phone");
                                    et_yzm.setText(YZM);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, true, true);
                break;
            case R.id.btn_register_signup:
                String yzm = et_yzm.getText().toString().trim();
                String pwd = et_pwd.getText().toString().trim();
                if (!CommonUtil.isMobileNumber(name)) {
                    CommonUtil.showToask(this, "手机号格式不正确");
                    return;
                }
                if (!name.equals(tel)) {
                    CommonUtil.showToask(this, "手机号码错误，请重新获取验证码");
                    return;
                }
                if (!yzm.equals(YZM)) {
                    CommonUtil.showToask(this, "验证码错误，请重新输入");
                    return;
                }
                if (pwd.length() < 6) {
                    CommonUtil.showToask(this, "密码长度不少于6位");
                    return;
                }
                break;
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!TextUtils.isEmpty(et_name.getText().toString())
                && !TextUtils.isEmpty(et_yzm.getText().toString())
                && !TextUtils.isEmpty(et_pwd.getText().toString())) {
            bt_sing.setBackgroundResource(R.drawable.rec_bg_blue);
            bt_sing.setClickable(true);
        } else {
            bt_sing.setBackgroundResource(R.drawable.rec_bg_gray);
            bt_sing.setClickable(false);
        }
    }

}
