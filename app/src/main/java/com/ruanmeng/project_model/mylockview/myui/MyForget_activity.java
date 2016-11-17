package com.ruanmeng.project_model.mylockview.myui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.mylockview.lockuitls.SPUtils;
import com.ruanmeng.utils.CommonUtil;

public class MyForget_activity extends AppCompatActivity {
    private EditText mEditAccount;
    private EditText mEditPsw;        private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_forget);


        initView();
    }

    private void initView() {


        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str_account, str_psw;
                str_account = mEditAccount.getText().toString().trim();
                str_psw = mEditPsw.getText().toString().trim();
                if (TextUtils.isEmpty(str_account)||TextUtils.isEmpty(str_psw))
                {


                    CommonUtil.showToask(MyForget_activity.this,"账号或密码不能为空！");
                    return;
                }
                SPUtils.put(MyForget_activity.this,"username",str_account);
                SPUtils.put(MyForget_activity.this,"userpsd",str_psw);

                finish();

            }
        });

        mEditAccount = (EditText) findViewById(R.id.edit_account);
        mEditPsw = (EditText) findViewById(R.id.edit_psw);





    }
}
