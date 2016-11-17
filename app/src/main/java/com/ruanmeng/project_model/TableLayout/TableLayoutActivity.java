package com.ruanmeng.project_model.TableLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class TableLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);
        Button button1=new Button(this);
        button1.setText("固定的page--->TabLayout");
        Button button2=new Button(this);
        button2.setText("more的page--->TabLayout");
        Button button3=new Button(this);
        button3.setText("图文TabTitle--->TabLayout");
        layout.addView(button1);
        layout.addView(button2);
        layout.addView(button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(0);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(1);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(2);
            }
        });
    }

    private void startIntent(int type){
        Intent intent=new Intent(this,TablayoutActivity.class);
        intent.putExtra("type",type);
        startActivity(intent);
    }

}
