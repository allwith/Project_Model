/*
 * Copyright 2015 chenupt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ruanmeng.project_model.DragTopLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.ruanmeng.project_model.R;

import github.chenupt.multiplemodel.BaseItemModel;

/**
 * Created by chenupt@gmail.com on 2015/1/18.
 * Description TODO
 */
public class CustomView extends BaseItemModel<String> {

    private TextView textView;

    public CustomView(Context context) {
        super(context);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.view_item_custom, this, true);
        textView = (TextView) findViewById(R.id.text);
    }

    @Override
    public void bindView() {
        textView.setText("item:" + viewPosition);
    }
}
