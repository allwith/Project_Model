package com.ruanmeng.project_model.DragTopLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;

import com.ruanmeng.project_model.R;

import de.greenrobot.event.EventBus;
import github.chenupt.dragtoplayout.AttachUtil;
import github.chenupt.multiplemodel.ModelListAdapter;

/**
 * Created by chenupt@gmail.com on 1/30/15.
 * Description :
 */
public class GridViewFragment extends Fragment {

    private GridView gridView;
    private ModelListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gridview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        gridView = (GridView) getView().findViewById(R.id.grid_view);
        adapter = new ModelListAdapter(getActivity(), DataService.getInstance().getModelManager());
        gridView.setAdapter(adapter);
        adapter.setList(DataService.getInstance().getList());
        adapter.notifyDataSetChanged();


        // attach top
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                EventBus.getDefault().post(AttachUtil.isAdapterViewAttach(view));
            }
        });

    }


}
