package com.ruanmeng.project_model.DragTopLayout;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

import com.ruanmeng.project_model.R;

import github.chenupt.dragtoplayout.AttachUtil;
import github.chenupt.multiplemodel.ModelListAdapter;


/**
 * Created by chenupt@gmail.com on 3/4/15.
 * Description :
 */
public class PullToRefreshActivity extends ActionBarActivity {

    private PullToRefreshTopLayout pullToRefreshTopLayout;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefresh);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("PullToRefresh");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pullToRefreshTopLayout = (PullToRefreshTopLayout) findViewById(R.id.pull_to_refresh_layout);
        listView = (ListView) findViewById(R.id.list_view);

        ModelListAdapter adapter = new ModelListAdapter(this, DataService.getInstance().getModelManager());
        listView.setAdapter(adapter);
        adapter.setList(DataService.getInstance().getList());
        adapter.notifyDataSetChanged();

        // attach top
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                pullToRefreshTopLayout.getRefreshableView().setTouchMode(AttachUtil.isAdapterViewAttach(view));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
