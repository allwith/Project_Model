/*
 * Copyright 2015 Eduard Ereza Martínez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ruanmeng.project_model.mycrash;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ruanmeng.project_model.R;


public  class ErrorActivity extends Activity {

    String errorInformation="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_error_activity);

        errorInformation = CrashManager.getAllErrorDetailsFromIntent(ErrorActivity.this, getIntent());

        Button btn_check = (Button) findViewById(R.id.btn_check);//查看
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ErrorActivity.this)
                        .setTitle("错误详情")
                        .setCancelable(true)
                        .setMessage(errorInformation)
                        .setPositiveButton("返回", null)
                        .setNeutralButton("复制", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                copyErrorToClipboard();
                                Toast.makeText(ErrorActivity.this,"已复制到剪切板",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        Button moreInfoButton = (Button) findViewById(R.id.customactivityoncrash_error_activity_more_info_button);//上传
        if (CrashManager.isShowErrorDetailsFromIntent(getIntent())) {
            moreInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadErrorMessage();//上传信息
                }
          });
        } else {
            moreInfoButton.setVisibility(View.GONE);
        }

        int defaultErrorActivityDrawableId = CrashManager.getDefaultErrorActivityDrawableIdFromIntent(getIntent());
        ImageView errorImageView = ((ImageView) findViewById(R.id.customactivityoncrash_error_activity_image));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            errorImageView.setImageDrawable(getResources().getDrawable(defaultErrorActivityDrawableId, getTheme()));
        } else {
            errorImageView.setImageDrawable(getResources().getDrawable(defaultErrorActivityDrawableId));
        }
    }

    /**
     * 复制到剪切板
     */
    private void copyErrorToClipboard() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(getString(R.string.customactivityoncrash_error_activity_error_details_clipboard_label), errorInformation);
            clipboard.setPrimaryClip(clip);
        } else {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            clipboard.setText(errorInformation);
        }
    }


    /**
     * 上传错误信息
     */
    void uploadErrorMessage(){
        final ProgressDialog md=new ProgressDialog(this);
        md.setMessage("正在上传，请稍后...");
        md.setCancelable(false);
        md.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                md.dismiss();
                Toast.makeText(ErrorActivity.this, "上传完成", Toast.LENGTH_SHORT).show();
            }
        },1500);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
                new AlertDialog.Builder(ErrorActivity.this)
                        .setTitle("提示")
                        .setCancelable(true)
                        .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNeutralButton("重新启动", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final Class<? extends Activity> restartActivityClass = CrashManager.getRestartActivityClassFromIntent(getIntent());
                                if (restartActivityClass != null) {
                                    Intent intent = new Intent(ErrorActivity.this, restartActivityClass);
                                    CrashManager.restartApplicationWithIntent(ErrorActivity.this, intent);
                                } else {
                                    CrashManager.closeApplication(ErrorActivity.this);
                                }
                            }
                        })
                        .show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
