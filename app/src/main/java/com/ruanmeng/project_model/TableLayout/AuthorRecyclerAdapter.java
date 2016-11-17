package com.ruanmeng.project_model.TableLayout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruanmeng.project_model.R;

import java.util.List;

/**
 * Created by Clock on 2016/08/01.
 */
public class AuthorRecyclerAdapter extends RecyclerView.Adapter<AuthorRecyclerAdapter.AuthorViewHolder> {


    private List<AuthorInfo> mAuthorInfoList;

    public AuthorRecyclerAdapter() {
    }

    public AuthorRecyclerAdapter(List<AuthorInfo> mAuthorInfoList) {
        this.mAuthorInfoList = mAuthorInfoList;
    }


    @Override
    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = inflater.inflate(R.layout.author_card_layout, parent, false);
        AuthorViewHolder viewHolder = new AuthorViewHolder(childView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AuthorViewHolder holder, int position) {
        if (mAuthorInfoList != null) {
            AuthorInfo authorInfo = mAuthorInfoList.get(position);
            holder.mNickNameView.setText(authorInfo.getNickName());
            holder.mMottoView.setText(authorInfo.getMotto());
        }

    }

    @Override
    public int getItemCount() {
        return mAuthorInfoList != null ? mAuthorInfoList.size() : 20;
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder {

        TextView mNickNameView;
        TextView mMottoView;

        public AuthorViewHolder(View itemView) {
            super(itemView);
            mNickNameView = (TextView) itemView.findViewById(R.id.tv_nickname);
            mMottoView = (TextView) itemView.findViewById(R.id.tv_motto);

        }
    }
}
