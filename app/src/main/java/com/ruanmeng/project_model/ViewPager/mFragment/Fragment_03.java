package com.ruanmeng.project_model.ViewPager.mFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruanmeng.project_model.R;

/**
 * Created by Administrator on 2016/11/2.
 */
public class Fragment_03 extends BaseFragment {

    /**
     * 预加载标志，默认值为false，设置为true，表示已经预加载完成，可以加载数据
     */
    private boolean isPrepared;


    //volatile的作用是： 作为指令关键字，确保本条指令不会因编译器的优化而省略，且要求每次直接读值.
    //一个定义为volatile的变量是说这变量可能会被意想不到地改变，
    //这样，编译器就不会去假设这个变量的值了。
    //精确地说就是，优化器在用到这个变量时必须每次都小心地重新读取这个变量的值，而不是使用保存在寄存器里的备份。
    public volatile static Fragment_03 fm_02;

    public static Fragment_03 getInstance() {

//         第一次判断是否为空
        if (fm_02 == null) {
//            上锁
            synchronized (Fragment_03.class) {
//                 第二次判断是否为空  多线程并发  同时走到这里的时候 需要添加处理

                if (fm_02 == null) {

                    fm_02 = new Fragment_03();
                }
            }

        }
        return fm_02;
    }

    private Fragment_03() {
//         私有化构造参数
    }
    public Fragment_03(Context context){


    }

    /**
     * Fragment生命周期方法，此view可改为自定义的布局
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lay_frag_03, container, false);
        //TODO 此处初始化view中各个控件
        isPrepared = true;
        setlazyLoad();
        return view;
    }

    /**
     * 加载数据的方法，只要保证isPrepared和isVisible都为true的时候才往下执行开始加载数据
     */
    @Override
    protected void setlazyLoad() {
        super.setlazyLoad();
        if (!isPrepared || !isVisible) {
            return;
        }
        //TODO 此处填充view中各个控件的数据
    }

}
