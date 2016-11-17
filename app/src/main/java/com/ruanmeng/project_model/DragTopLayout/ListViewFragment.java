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
 * imitations under the License.
 */

package com.ruanmeng.project_model.DragTopLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ruanmeng.project_model.R;

import de.greenrobot.event.EventBus;
import github.chenupt.dragtoplayout.AttachUtil;
import github.chenupt.multiplemodel.ModelListAdapter;

/**
 * Created by chenupt@gmail.com on 1/23/15.
 * Description :
 */
public class ListViewFragment extends Fragment {

    private ListView listView;
    private ModelListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        listView = (ListView) getView().findViewById(R.id.list_view);
        adapter = new ModelListAdapter(getActivity(), DataService.getInstance().getModelManager());
        listView.setAdapter(adapter);
        adapter.setList(DataService.getInstance().getList());
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Clicked " + position, Toast.LENGTH_SHORT).show();
            }
        });

        // attach top
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                EventBus.getDefault().post(AttachUtil.isAdapterViewAttach(view));
            }
        });
    }
}
