package com.ruanmeng.animator;

import android.support.v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;

/**
 * 动画生成器
 */
public class ItemAnimatorFactory {
    static public RecyclerView.ItemAnimator slidein() {
        SlideInUpDelayedAnimator animator = new SlideInUpDelayedAnimator(new DecelerateInterpolator(1.2f));
        animator.setAddDuration(600);
        return animator;
    }

    static  public  RecyclerView.ItemAnimator  slidebootom(){

        SlideInUpDelayedAnimator animator = new SlideInUpDelayedAnimator(new DecelerateInterpolator(1.2f));
        animator.setAddDuration(600);
        return animator;
    }
}
