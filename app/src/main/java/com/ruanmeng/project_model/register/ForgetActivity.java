package com.ruanmeng.project_model.register;

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

public class ForgetActivity extends BaseActivity {

    @BindView(R.id.et_forget_tel)
    EditText et_name;
    @BindView(R.id.et_forget_pwd)
    EditText et_pwd;
    @BindView(R.id.et_forget_comfirm)
    EditText et_confirm;
    @BindView(R.id.et_forget_yzm)
    EditText et_yzm;
    @BindView(R.id.btn_forget_yzm)
    Button bt_yzm;
    @BindView(R.id.btn_forget_confirm)
    Button bt_confirm;

    // 验证码
    private int time_count;
    private Runnable thread;
    private String tel;
    private String YZM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
        init_title();
        changeTitle("找回密码", null);
    }

    @Override
    public void init_title() {
        super.init_title();

        bt_confirm.setBackgroundResource(R.drawable.rec_bg_gray);
        bt_confirm.setClickable(false);

        et_name.addTextChangedListener(this);
        et_yzm.addTextChangedListener(this);
        et_pwd.addTextChangedListener(this);
        et_confirm.addTextChangedListener(this);
    }

    @Override
    public void doClick(View v) {
        super.doClick(v);
        final String name = et_name.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btn_forget_yzm:
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
                            bt_yzm.setBackgroundResource(R.drawable.rec_stroke_red);
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
                                    bt_yzm.setBackgroundResource(R.drawable.rec_stroke_grey);
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
            case R.id.btn_forget_confirm:
                String yzm = et_yzm.getText().toString().trim();
                String pwd = et_pwd.getText().toString().trim();
                String confirm = et_confirm.getText().toString().trim();
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
                if (pwd.length() < 8) {
                    CommonUtil.showToask(this, "新密码长度不少于8位");
                    return;
                }
                if (confirm.length() < 8) {
                    CommonUtil.showToask(this, "确认密码长度不少于8位");
                    return;
                }
                if (!pwd.equals(confirm)) {
                    CommonUtil.showToask(this, "密码不一致，请重新输入");
                    return;
                }
                break;
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!TextUtils.isEmpty(et_name.getText().toString())
                && !TextUtils.isEmpty(et_yzm.getText().toString())
                && !TextUtils.isEmpty(et_pwd.getText().toString())
                && !TextUtils.isEmpty(et_confirm.getText().toString())) {
            bt_confirm.setBackgroundResource(R.drawable.rec_bg_blue);
            bt_confirm.setClickable(true);
        } else {
            bt_confirm.setBackgroundResource(R.drawable.rec_bg_gray);
            bt_confirm.setClickable(false);
        }
    }

}
