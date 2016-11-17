package com.ruanmeng.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class ParentListView extends ListView {

	public ParentListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ParentListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ParentListView(Context context) {
		super(context);
	}

	//将 onInterceptTouchEvent的返回值设置为false，取消其对触摸事件的处理，将事件分发给子view

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return false;
	}

}
