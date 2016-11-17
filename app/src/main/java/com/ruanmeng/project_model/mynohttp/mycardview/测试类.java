package com.ruanmeng.project_model.mynohttp.mycardview;

import android.content.Context;

import com.ruanmeng.utils.CommonUtil;

/**
 * Created by Administrator on 2016/9/18.
 */
public class 测试类 {
//       单例

    //volatile的作用是： 作为指令关键字，确保本条指令不会因编译器的优化而省略，且要求每次直接读值.
    //一个定义为volatile的变量是说这变量可能会被意想不到地改变，
    //这样，编译器就不会去假设这个变量的值了。
    //精确地说就是，优化器在用到这个变量时必须每次都小心地重新读取这个变量的值，而不是使用保存在寄存器里的备份。
    public volatile static 测试类 测试;


    public static 测试类 getInstance() {
        //第一次判断是否为空
        if (测试 == null) {
            //锁
            synchronized (测试类.class) {
                //第二次判断是否为空 多线程同时走到这里的时候，需要这样优化处理

                if (测试 == null) {
                    测试 = new 测试类();
                }
            }

        }
        return 测试;
    }

    private 测试类() {
        //构造函数 逻辑处理
    }


    public 测试类(Context context) {
//        CommonUtil.showToask(context,"这是一个测试类！");
    }

    public void showToast(Context context, String string) {

        CommonUtil.showToask(context, "这是一个测试类！");


    }
}
