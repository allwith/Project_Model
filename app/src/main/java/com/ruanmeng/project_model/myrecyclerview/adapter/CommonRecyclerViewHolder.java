package com.ruanmeng.project_model.myrecyclerview.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by cxj on 2016/4/28.
 */
public class CommonRecyclerViewHolder extends RecyclerView.ViewHolder {

    /**
     * 用来保存条目视图里面所有的控件
     */
    private SparseArray<View> mViews = null;


    private Context mContext;

    /**
     * 构造函数
     *
     * @param itemView
     */
//    public CommonRecyclerViewHolder(View itemView) {
//        super(itemView);
//        mViews = new SparseArray<View>();
////        CommonRecyclerViewHolder()
//    }
    public CommonRecyclerViewHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
        mViews = new SparseArray<View>();
    }

    /**
     * 根据控件id获取控件对象
     *
     * @param viewId
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getView(int viewId) {

        // 从集合中根据这个id获取view视图对象
        View view = mViews.get(viewId);

        // 如果为空，说明是第一次获取，里面没有，那就在布局文件中找到这个控件，并且存进集合中
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        // 返回控件对象
        return (T) view;
    }

    /**
     * 为TextView设置文本,按钮也可以用这个方法,button是textView的子类
     *
     * @param textViewId
     * @param content
     */
    public void setTvText(int textViewId, String content) {
        ((TextView) getView(textViewId)).setText(content);
    }


    /**
     * 设置按钮的文字显示
     *
     * @param btn_id
     * @param btn_content
     */
    public void setBtnText(int btn_id, String btn_content) {


        ((Button) getView(btn_id)).setText(btn_content);
    }

    /**
     * 设置Button  的  背景色
     *
     * @param btn_id
     * @param color
     */
    public void setBtnBg_Color(int btn_id, int color) {
        ((Button) getView(btn_id)).setBackgroundColor(mContext.getResources().getColor(color));
    }

    /**
     * 设置Button  的  背景色
     *
     * @param btn_id
     * @param resultid
     */
    public void setBtnBg_Drawable(int btn_id, int resultid) {
        ((Button) getView(btn_id)).setBackground(mContext.getResources().getDrawable(resultid));
    }

    /**
     * 设置Button  的  背景色
     *
     * @param btn_id
     * @param resources_id
     */
    public void setBtnBg_Resources(int btn_id, int resources_id) {
        ((Button) getView(btn_id)).setBackgroundResource(resources_id);
    }

    /**
     * 为ImageView设置图片
     *
     * @param iv
     * @param imageId
     */
    public void setImage(ImageView iv, int imageId) {
        iv.setImageResource(imageId);
    }

    /**
     * 为ImageView设置图片
     *
     * @param imgId
     * @param imageId
     */
    public void setImage(int imgId, int imageId) {
        ((ImageView) getView(imgId)).setImageResource(imageId);
    }

    /**
     * 为imageview 设置图片
     *
     * @param imgId
     * @param bitmap
     */
    public void setImage(int imgId, Bitmap bitmap) {
        ((ImageView) getView(imgId)).setImageBitmap(bitmap);

    }

    /**
     * 为imageview 设置图片
     *
     * @param img_head
     * @param drawable
     */
    public void setImage(int img_head, Drawable drawable) {
        ((ImageView) getView(img_head)).setImageDrawable(drawable);
    }
}
