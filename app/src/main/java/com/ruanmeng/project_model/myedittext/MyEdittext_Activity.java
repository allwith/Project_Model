package com.ruanmeng.project_model.myedittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.myedittext.view.AnFQNumEditText;
import com.ruanmeng.project_model.myedittext.view.XEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyEdittext_Activity extends AppCompatActivity {


    //      电话号
    @BindView(R.id.edit_phone)
    XEditText editPhone;
    @BindView(R.id.tv_show_phone)
    TextView tvShowPhone;

    //       银行卡

    @BindView(R.id.edit_money)
    XEditText editMoney;
    @BindView(R.id.tv_show_money)
    TextView tvShowMoney;

    //     身份证号
    @BindView(R.id.edit_idcard)
    XEditText editIdcard;
    @BindView(R.id.tv_show_idcard)
    TextView tvShowIdcard;
    @BindView(R.id.btn_sub)
    Button btnSub;
    @BindView(R.id.edit_autonums)
    AnFQNumEditText editAutonums;


//     5.0 输入框
//    @BindView(R.id.acet_password)
//    AppCompatEditText acetPassword;
//    @BindView(R.id.til_password)
//    TextInputLayout tilPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_edittext);
        ButterKnife.bind(this);


        init();

    }

    private void init() {
//            银行卡号  长度不一定
        editMoney.setMaxLength(16 + 3);
        editMoney.setSeparator("-");
        editMoney.setPattern(new int[]{4, 4, 4, 4});
//         电话号
        editPhone.setMaxLength(11 + 2);
        editPhone.setSeparator(" ");
        editPhone.setPattern(new int[]{3, 4, 4});
// 身份证号  410185  1992 0801 1517
        editIdcard.setMaxLength(18 + 3);
        editIdcard.setSeparator("-");
        editIdcard.setPattern(new int[]{6, 4, 4, 4});


        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str_phone = editPhone.getText().toString().trim();
                str_phone = str_phone.replaceAll(" ", "");

                String str_money = editMoney.getText().toString().trim();
                str_money = str_money.replaceAll("-", "");

                String str_idcard = editIdcard.getText().toString().trim();
                str_idcard = str_idcard.replaceAll("-", "");

                tvShowPhone.setText(str_phone);
                tvShowMoney.setText(str_money);
                tvShowIdcard.setText(str_idcard);


            }
        });

//        计数edittext 输入框
        editAutonums.setEtHint("请输入内容")// 提示文字
                .setEtMinHeight(200) //设置最小高度，单位px
                .setLength(50)//设置总字数
                .setType(AnFQNumEditText.PERCENTAGE)//TextView显示类型(SINGULAR单数类型)(PERCENTAGE百分比类型)
                .setLineColor("#3F51B5")//设置横线颜色
                .setEtColor(R.color.Red)  //  设置字体颜色值
                .setEtTextSize(14)     // 设置字体大小（包括hint 字体的大小）
                .show()
        ;

    }
}
